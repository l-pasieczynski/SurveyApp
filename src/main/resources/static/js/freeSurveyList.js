$(function () {


    let surveyDiv = $(".free-survey-list");
    surveyDiv.on("click", ".free-survey-title", openSurvey);

    refreshList();

    function refreshList() {
        showSurvey(surveyDiv);
    }

    function showSurvey(renderingPoint) {
        $.ajax({
            url: "http://localhost:8080/surveys",
            type: "GET",
            dataType: "json",
        }).done(function (surveyList) {
            renderingPoint.empty();

            for (let i = 0; i < surveyList.length; i++) {
                let surveyListElement = surveyList[i];

                let descriptionDiv = $('<div class="description">');
                let delBtn = $('<button class="delete-book-btn">Usuń</button>');
                let titleDiv = $('<div class="book-title">');
                titleDiv.text(book.title);
                titleDiv.data("id", book.id);
                titleDiv.append(delBtn);
                titleDiv.append(descriptionDiv);

                renderingPoint.append(titleDiv);
            }
        }).fail(function (xhr, status, err) {
            console.log(xhr, status, err);
        });

    }


});