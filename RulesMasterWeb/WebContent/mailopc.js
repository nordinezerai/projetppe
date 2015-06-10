var ruleDelete = function (ruleId) {
    var confirmed = window.confirm("Voulez-vous supprimer la règle sélectionnée?");
    if (!confirmed) {
        return;
    }

    console.log('deleting rule: ' + ruleId);

    $.ajax({
        type: "DELETE",
        url: backend() + '/rules/' + ruleId
    }).then(function (data) {
        console.log(data);

        // reload the page
        window.location.reload();
    });
};

var ruleAddAfter = function (ruleId) {
    console.log('starting to add rule after: ' + ruleId);

    var dialog = $('#ruleAddDialog');
    dialog.attr('data-previous-rule-id', ruleId);
    dialog.attr('data-current-rule-id', null);

    dialog.modal();
};

var ruleEdit = function (ruleId) {
    console.log('starting to edit rule: ' + ruleId);

    var dialog = $('#ruleAddDialog');
    dialog.attr('data-current-rule-id', ruleId);
    dialog.attr('data-previous-rule-id', null);

    dialog.find('#ruleSender').val($('#from-' + ruleId).text());
    dialog.find('#ruleSubject').val($('#subject-' + ruleId).text());
    dialog.find('#ruleBody').val($('#body-' + ruleId).text());
    dialog.find('#ruleDestination').val($('#destination-' + ruleId).text());

    dialog.modal();
};

var ruleAddOrEdit = function () {
    // get the previous or current rule ID to determine if we're adding or editing
    var dialog = $('#ruleAddDialog');
    var prevRuleId = dialog.attr('data-previous-rule-id');
    var currRuleId = dialog.attr('data-current-rule-id');

    var newRule;
    var method;
    var destinationUrl;

    if (((prevRuleId === undefined) || (prevRuleId === null)) && ((currRuleId !== undefined) && (currRuleId !== null))) {
        console.log('editing rule: ' + prevRuleId);
        // prepare the modified rule to update
        // method = 'PUT'; // see comment server-side
        method = 'POST';
        destinationUrl = '/rules/' + currRuleId;
        newRule = {
            fromQuery: $('#ruleSender').val(),
            subjectQuery: $('#ruleSubject').val(),
            bodyQuery: $('#ruleBody').val(),
            destinationFolder: $('#ruleDestination').val()
        };

    } else if (((prevRuleId !== undefined) && (prevRuleId !== null)) && ((currRuleId === undefined) || (currRuleId === null))) {
        console.log('adding rule after: ' + prevRuleId);
        // prepare the new rule to create
        method = 'POST';
        destinationUrl = '/rules/add';
        newRule = {
            fromQuery: $('#ruleSender').val(),
            subjectQuery: $('#ruleSubject').val(),
            bodyQuery: $('#ruleBody').val(),
            destinationFolder: $('#ruleDestination').val(),
            previousRuleId: prevRuleId
        };

    } else {
        console.log('illegal modal state: prev=' + prevRuleId + ", curr=" + currRuleId);
    }

    // add/edit the rule with a REST call
    $.ajax({
        type: method,
        url: backend() + destinationUrl,
        data: newRule
    }).then(function (data) {
        console.log(data);
        // toggle visibility
        dialog.modal('hide');

        // reload the page
        window.location.reload();
    });
};

var backend = function () {
    // FIXME origin is not portable, use window.location.protocol + ...
    return window.location.origin;
};

var loadExchangeState = function () {
    console.log('starting to load the Exchange state');
    var button = $('#load-exchange-state-btn');
    button.html('Charger état courant Exchange: EN COURS');

    $.ajax({
        url: backend() + '/exchange/load'
    }).then(function (data) {
        console.log(data);
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
        button.html('Charger état courant Exchange: FAIT');
    });
};

var testRules = function () {
    console.log('starting the rules\' test');
    var button = $('#test-rules-btn');
    button.html('Tester les règles: EN COURS');

    $.ajax({
        url: backend() + '/rules/test'
    }).then(function (data) {
        console.log(data);
        data.forEach(function (result) {
            // update the match count
            var currMatchCount = $('#matchCount-' + result.id);
            currMatchCount.html('' + result.matchCount);

            // update the error state
            var currLine = $('#destination-' + result.id);
            if (result.errorMessage) {
                currLine.addClass('danger');
            } else {
                currLine.removeClass('danger');
            }
        });
        button.html('Tester les règles: FAIT');
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
    });
};

var executeRules = function () {
    console.log('starting the rules\' execution');
    var button = $('#execute-rules-btn');
    button.html('Exécuter les règles: EN COURS');

    $.ajax({
        url: backend() + '/rules/execute'
    }).then(function (data) {
        console.log(data);
        data.forEach(function (result) {
            // update the match count
            var currMatchCount = $('#matchCount-' + result.id);
            currMatchCount.html('' + result.matchCount);

            // update the error state
            var currLine = $('#destination-' + result.id);
            if (result.errorMessage) {
                currLine.addClass('danger');
            } else {
                currLine.removeClass('danger');
            }
        });
        button.html('Exécuter les règles: FAIT');
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
    });
};
