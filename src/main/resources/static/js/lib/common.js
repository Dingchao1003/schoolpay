/**
 * Created by yjq on 2018/8/12.
 */
define(function () {
    
    var common = {};
    
    common.warnning = function (content) {
        var warnningDialog = "";
        var noWarnningExist = $("#warnningDialog").length == 0;
        var body = $("body");
        if (noWarnningExist){
            warnningDialog = $("<div id='warnningDialog' class='warnningDialog'></div>");
        }else{
            $("#warnningDialog").empty();
            warnningDialog = $("#warnningDialog");
        }
        var alertDialog = $("<div class='alert alert-danger'></div>");
        var alertContent = $("<span class='alert-text'>There is an error here : </span>");
        var alertCause = $("<h4 class='no-margins'>"+content+"</h4>");

        alertDialog.append(alertContent,alertCause);
        warnningDialog.append(alertDialog);

        if (noWarnningExist){
            body.append(warnningDialog);
        }
        warnningDialog.fadeIn(100).fadeOut(3000);
    }

    return common;
})
