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

    common.openAjaxDialog = function (title,url,dom,callback) {
        var opitions = $.extend({},{
            title:title || "系统提醒您 !",
            url:url || "",
            dom:dom || "",
            cancleBtn:true,
            cancleText:"取消",
            okBtn:true,
            okBtnText:"确定"
        },opitions || {})

        $("#ajaxDialog").remove();
        var $dialog = $('<div id="ajaxDialog" class="modal absolute-center bg-muted">' +
            '<div class="modal-dialog no-margins"><div class="modal-content">' +
            '<div class="modal-header">' +
            '<a href="javascript:void(0)" class="close">&times;</a>' +
            '<h4 class="modal-title">' + opitions.title + '</h4></div>' +
            '<div class="modal-body no-paddings"><div class="modal-body-content"></div></div>' +
            '</div></div></div>');

        var pageBody = $('body');
        pageBody.append($dialog);
        $("#ajaxDialog").fadeIn();
        var content = $dialog.find(".modal-body-content");
        if(common.isEmpty(url)){
            content.append(dom);
        }else{
            var iframe = $('<iframe id="mainIframe" name="mainIframe" src="'+opitions.url+'" frameborder="0" scrolling="auto" ></iframe>');
            content.append(iframe);
        }
        if(opitions.cancleBtn || opitions.okBtn){
            var footer = $('<div class="modal-footer"></div>');
            if(opitions.cancleBtn){
                var cancel = $('<button type="button" class="btn-cancel btn btn-default">'+opitions.cancleText+'</button>');
                footer.append(cancel);
                cancel.bind("click",function () {
                    $("#ajaxDialog").fadeOut();
                })
            }
            if(opitions.okBtn){
                var ok = $('<button type="button" class="btn-ok btn btn-default">'+opitions.okBtnText+'</button>');
                footer.append(ok);
                ok.bind("click",function () {
                    if(common.isEmpty(url)){
                        $("#ajaxDialog").fadeOut();
                    }else{
                        var form = $dialog.find("form");
                        var form_action = $dialog.find("form").attr("action");
                        var form_data = $(form).serialize();
                        common.ajax({
                            url:form_action,
                            data:form_data,
                        },function (data) {
                            callback(data);
                            $("#ajaxDialog").fadeOut();
                        })
                    }
                })
            }
            $dialog.append(footer);
        }
    }
    
    common.isEmpty = function (obj) {
        if(typeof (obj) == "object"){
            return obj.length == 0;
        }
        if(typeof  (obj) == "string"){
            return obj == "" || obj == null;
        }
    }
    
    common.ajax = function (obj,middleware) {
        var deffered = $.Deferred();
        var _this = this;
        _this.ajaxLoading();
        $.ajax({
            url: obj.url,
            type: obj.type || "GET",
            data: obj.data || {},
            async:obj.async || true,
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
