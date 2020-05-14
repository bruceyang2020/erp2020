$(document).ready(function () {



    $('#ceo-cw').click(function () {
    $('.pop').show();
    });


    $('#jz').click(function () {
        $('.pop-jz').show()
    });

    $('#menuUserSet').click(function () {
        $('.pop-userset').show()
    });



    $('.pop-close').click(function () {
        $('.pop-jz').hide();
    });

    $('.pop-close').click(function () {
        $('.pop-userset').hide();
    });




    $('.pop-close').click(function () {
        $('.pop').hide();
    });
    $('#ceo-sc').click(function () {
        $('.pop2').show();
    });
    $('.pop-close').click(function () {
        $('.pop2').hide();
    });
    $('#order').click(function () {
        $('.pop3').show();
    });
    $('.pop-close').click(function () {
        $('.pop3').hide();
    });

    $('.pop-close').click(function () {
        $('.pop4').hide();
    });


    //长贷
    $('#ceo-cz-c').click(function () {
        var period = $('#currentAp').val();
        if(Number(period)%4 != 1  )
        {
            alert("当前会计期间不可执行长贷筹资。");
            return;
        }
        $('.pop-s1').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s1').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s1').hide();
    });

    //短贷
    $('#ceo-cz-d').click(function () {
        $('.pop-s2').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s2').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s2').hide();
    });


    //高利贷
    $('#ceo-cz-gl').click(function () {

        $('.pop-s3').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s3').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s3').hide();
    });

    //投资
    $('#ceo-tz-ma1').click(function () {
        var period = $('#currentAp').val();
        //H 年末
        if(Number(period)%4 != 0  )
        {
            alert("当前会计期间不可市场开拓哦。");
            return;
        }
        $('.pop-s4').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s4').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s4').hide();
    });

    $('#ceo-tz-ma2').click(function () {
        var period = $('#currentAp').val();
        //H 年末
        if(Number(period)%4 != 0  )
        {
            alert("当前会计期间不可市场开拓哦。");
            return;
        }
        $('.pop-s5').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s5').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s5').hide();
    });

    $('#ceo-tz-ma3').click(function () {
        var period = $('#currentAp').val();
        //H 年末
        if(Number(period)%4 != 0  )
        {
            alert("当前会计期间不可市场开拓哦。");
            return;
        }
        $('.pop-s6').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s6').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s6').hide();
    });

    $('#ceo-tz-ma4').click(function () {
        var period = $('#currentAp').val();
        //H 年末
        if(Number(period)%4 != 0  )
        {
            alert("当前会计期间不可市场开拓哦。");
            return;
        }
        $('.pop-s7').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s7').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s7').hide();
    });

    $('#ceo-tz-pr1').click(function () {
        $('.pop-s8').show()
    });
    $('.pop-ok').click(function () {
        $('.pop-s8').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s8').hide();
    });

    $('#ceo-tz-pr2').click(function () {
        $('.pop-s9').show()
    });
    $('.pop-ok').click(function () {
        $('.pop-s9').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s9').hide();
    });

    $('#ceo-tz-pr3').click(function () {
        $('.pop-s10').show();
    });

    $('.pop-ok').click(function () {
        $('.pop-s10').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s10').hide();
    });

   $('#ceo-tz-pr4').click(function () {
        $('.pop-s11').show();
    });

    $('.pop-ok').click(function () {
        $('.pop-s11').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s11').hide();
    });


  // H ISO认证-ISO9K
    $('#ceo-tz-iso1').click(function () {
        var period = $('#currentAp').val();
        //H 年末
        if(Number(period)%4 != 0  )
        {
            alert("当前会计期间不可获得证书哦。");
            return;
        }
        $('.pop-s12').show();
    });

    $('.pop-ok').click(function () {
        $('.pop-s12').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s12').hide();
    });



    //H ISO认证-ISO14K
    $('#ceo-tz-iso2').click(function () {
        var period = $('#currentAp').val();
        //H 年末
        if(Number(period)%4 != 0  )
        {
            alert("当前会计期间不可获得证书哦。");
            return;
        }
        $('.pop-s13').show()
    });
    $('.pop-ok').click(function () {
        $('.pop-s13').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s13').hide();
    });






    //订货会弹窗-广告费
    $('#order').click(function () {
        var period = $('#currentAp').val();
        //H 年末
        if(Number(period)%4 != 1  )
        {
            alert("当前会计期间不能参加订货会。");
            return;
        }
       $('.pop-advertise').show();
        $('#advertise-wrap').attr('src', $('#advertise-wrap').attr('src'));  //需要刷新iframe的子页面-广告页。



    });
    $('#ok-tomarketing').click(function () {

        $('.pop-advertise').hide(); //广告页面关闭
        $('.pop-marketing').show(); //市场订单页面打开
        $('#order-wrap').attr('src', $('#order-wrap').attr('src'));  //需要刷新iframe的子页面-订货会页面。




    });


    $('.pop-cancel').click(function () {
        $('.pop-advertise').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-advertise').hide();
    });

    $('.pop-cancel').click(function () {
        $('.pop-marketing').hide();
    });


    $('.pop-cancel').click(function () {
        $('.pop-p1').hide()
    });
    $('#p2-list').click(function () {
        $('.pop-p2').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-p2').hide()
    });
    $('#p3-list').click(function () {
        $('.pop-p3').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-p3').hide()
    });
    $('#p4-list').click(function () {
        $('.pop-p4').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-p4').hide()
    });



    //原材料订单的弹出页面
    $('#r1-1').click(function () {
        $('.pop-r1-1').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r1-1').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r1-1').hide();
    });
    $('#r2-1').click(function () {
        $('.pop-r2-1').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r2-1').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r2-1').hide();
    });

    $('#r3-2').click(function () {
        $('.pop-r3-2').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r3-2').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r3-2').hide();
    });

    $('#r4-2').click(function () {
        $('.pop-r4-2').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r4-2').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r4-2').hide();
    });

    //应收账款
    $('#fin-1').click(function(){
      $('.pop-f1-1').show()
    });
    $('#fin-2').click(function(){
        $('.pop-f1-1').show()
    });
    $('#fin-3').click(function(){
        $('.pop-f1-1').show()
    });
    $('#fin-4').click(function(){
        $('.pop-f1-1').show()
    });


    $('.pop-ok').click(function(){
        $('.pop-f1-1').hide()
    });
    $('.pop-cancel').click(function () {
        $('.pop-f1-1').hide();
    });





$(function(){
    $('#tabs a').click(function(e) {
        e.preventDefault();
        $('#tabs li').removeClass("current").removeClass("hoverItem");
        $(this).parent().addClass("current");
        $("#pop-content div").removeClass("show");
        $('#' + $(this).attr('title')).addClass('show');
        // alert($(this).attr('title'));
        if($(this).attr('title')=="tab1"){
            //打开第一页操作
        }
        if($(this).attr('title')=="tab2"){
            //打开第二页操作
        }
        if($(this).attr('title')=="tab3"){
            //打开第三页操作
        }
        if($(this).attr('title')=="tab4"){
            //打开第四页操作
        }
    });
    $('#tabs a').hover(function(){
        if(!$(this).parent().hasClass("current")){
            $(this).parent().addClass("hoverItem");
        }}, function(){
        $(this).parent().removeClass("hoverItem");
    });


    function reinitIframe() {
        var iframe = document.getElementById("marketingpre-wrap");
        try {
            var bHeight = iframe.contentWindow.document.body.scrollHeight;

            iframe.height = bHeight;
        } catch (ex) { };

        var iframe = document.getElementById("marketingprePriceTotal-wrap");
        try {
            var bHeight = iframe.contentWindow.document.body.scrollHeight;

            iframe.height = bHeight;
        } catch (ex) { };



        var iframe = document.getElementById("advertise-wrap");
        try {
            var bHeight = iframe.contentWindow.document.body.scrollHeight;
            iframe.height = bHeight;
        } catch (ex) { }


    }})
});




/*  function ChangeStyle(StID)
    {
        var div1 = $("#ceo-tz-ma1");
        for (var i = 0; i < sts.length; i++) {
            if (sts[i]!=StID)
            {
                if (div1.hasClass(sts[i]))
                {
                    div1.addClass(sts[i+1]);
                }
            }
        }
        div1.addClass(StID);
        //div1.toggleClass(StID);
    }*/
