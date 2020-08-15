$(function(){

    let addSurvey = $('#addSurvey');
    let visible = $('#visible');
    let radio = $('#radio');
    let surveyform = $('#survey-form');
    let radioquestion = "costam";

    addSurvey.on('click',visibles);

    function visibles(){
        visible.toggleClass("btn-visible")

    }
    radio.on('click',addRadio).one('click',addRadioTitle);

    function addRadioTitle(){
        let radioDiv =$('<dvi>' +
            '<legend>' +
            '<input type="text" placeholder="write question"></legend></dvi>')
        surveyform.after(radioDiv)
    }

    function addRadio(){
        let newRadio = $('<div><input type="radio" id="radio-primary"><span id="spanRadio">AddAnswer</span></div>')
        surveyform.after(newRadio);

    }
    $('#spanRadio').on('click',addAnswer);

    function addAnswer(){
        $('#radio-primary').prop('value','ala');
    }



});