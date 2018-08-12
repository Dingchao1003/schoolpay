/**
 * Created by yjq on 2018/8/12.
 */
define(function () {
    
    var common = {};
    common.body = $("body");
    
    common.warnning = function (content) {
        var warnningDialog = "";
        var noWarnningExist = $("#warnningDialog").length == 0;
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
            this.body.append(warnningDialog);
        }
        warnningDialog.fadeIn(100).fadeOut(3000);
    }
    
    common.ajax = function (obj,middleware) {
        var deffered = $.Deferred();
        var _this = this;
        _this.ajaxLoading();
        $.ajax({
            url: obj.url,
            type: obj.type || "GET",
            data: obj.data || {},
            dataType: obj.dataType || "json",
            success:function (data) {
                if (data.code == 200){
                    deffered.reject(data);
                }else{
                    deffered.resolve(data);
                }
                _this.ajaxLoadingComplete();
            },
            error:function () {
                deffered.reject("接口出错，请重试或者联系管理员");
            }
        });

        if(!middleware){
            middleware = function () {}
        }
        return deffered.done(middleware).fail(function () {})
    }

    common.ajaxLoading = function () {
        if($("#ajaxLoading").length == 0){
            var loading = $("<div id='ajaxLoading'></div>");
            var loading_pic = $("<span class='fa fa-spinner loading-circle'></span>");
            loading.append(loading_pic);
            this.body.append(loading);
        }
        $("#ajaxLoading").show();
    }
    common.ajaxLoadingComplete = function () {
        $("#ajaxLoading").hide();
    }

    return common;
})
