
$(function(){

    let form = $("#form");
    let table=$("#survey-table");
    let submit=$('#submit');

    refreshSurveyList();

    function refreshSurveyList(){
        renderSurveyList(form);
    }
    function renderSurveyList(renderingPoint){
        let url = "http://localhost:8080/survey/rest/getSurvey";

        function getSurveyListSuccess(survey){

            let titleDiv = $('<h1 class="survey-title" style="text-align: -moz-left">')
            titleDiv.text(survey.name)
            renderingPoint.before(titleDiv)

            for(let i=0; i<survey.questions.length;i++){
                    let  questionsTitle = survey.questions[i].question;
                    let trTitle=$("<tr><th></th></tr>")
                    let questionDiv =$('<h4 class="question-title" style="margin-top: 2%">')
                    questionDiv.text(questionsTitle);

                    trTitle.find("th").append(questionDiv);
                    table.append(trTitle);

                    let questionType = survey.questions[i].questionType;

                    if(questionType==="text"){

                        let textInput = $('<input style="margin-top: 4%">')
                        textInput.attr('type','text')
                        textInput.attr('name',questionsTitle);
                        questionDiv.append("<div>");
                        questionDiv.append(textInput);

                    }else{

                    for(let j=0;j<survey.questions[i].answer.length;j++){


                        let answerInput = $('<input style="margin-top: 4%">');
                        let answerLabel = $('<label>')


                            answerLabel.attr('for',j+1);
                            let answerTitle = survey.questions[i].answer[j].answer;
                            answerLabel.html(answerTitle);
                            answerInput.attr('type',questionType)
                            answerInput.attr('id',j+1);
                            answerInput.text(answerTitle)

                            if(questionType === "radio" || questionType === "checkbox") {
                                answerInput.attr("name", questionsTitle);
                            }

                            answerInput.attr("value",answerTitle);
                            questionDiv.append("<div>");
                            questionDiv.append(answerInput).append(answerTitle);
                            answerInput.append(answerLabel);


                        }
                    }
                }
        }
        sendGenericRequest(url, "GET", "json", getSurveyListSuccess);
    }

    submit.on('click',sendResult);

    function sendResult(){
        let survey = function (name,question){
            this.name=name;
            this.question=question;
        }
        let question = function(question,answer){
            this.question=question;
            this.answer=answer;
        }
        let answer = function (name){
            this.name=name;
        }






        let query = $('h4');
        let radio = $('input:checked');
        let title = $('.survey-title');

        let surveySend = new survey();
        surveySend.name=title;

        console.log(surveySend);


        query.find('input').each(function (index,element){

            if($(element).attr('type')==='text'){
                console.log($(element).attr('name'));
                console.log($(element).val());

                let questionText= new question();
                questionText.question=$(element).attr('name');
                let answerText= new answer();
                answerText.name=$(element).val();
                questionText.answer=answerText;

                surveySend.question+=questionText;

            }
        });

        radio.each(function (index,element){
            console.log($(element).attr('name'));
            console.log($(element).val());

            let questionRadio= new question();
            questionRadio.question=$(element).attr('name');
            let answerRadio= new answer();
            answerRadio.name=$(element).val();
            questionRadio.answer=answerRadio;

            surveySend.question+=questionRadio;

        });

        return false



    }

    function sendGenericRequest(url, type, data, successHandlerFn){
        $.ajax({
            url : url,
            type : type,
            data : data === undefined ? "" : JSON.stringify(data),
            contentType : "application/json; charset=utf-8",
            dataType : "json",
        }).done(function(dataReturnedByServer){
            if(successHandlerFn !== undefined){
                successHandlerFn(dataReturnedByServer);
            }
        }).fail(function(xhr, status, err){
            console.log(xhr, status, err);
        });
    }

});