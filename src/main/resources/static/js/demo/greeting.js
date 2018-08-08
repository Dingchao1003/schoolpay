require(['/js/lib/require.config.js'], function () {
    require([ 'jquery','bootstrap','slimscroll','menu','inspinia'],
        function($) {

            $(function(){
                alert("我的儿（余建全），页面加载完成了，require.js成功奏效。");
            });

        });
});