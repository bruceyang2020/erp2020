
$(document).ready(function () {

    var myJson = { myname :'0000'};
    //获取当前用户、当前会计期间、公司ID，初始化前端对应的全局参数
    $.ajax({
        type: "post",
        dataType: "json",
        data: JSON.stringify(myJson),
        url: "/SysUser/getCurrentInfo?tm="+new Date().getTime(),
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            $("#currentUser").val(data.currentUser);

            if(   $("#currentAp").val() =='')
            {
                $("#currentAp").val("1");
                $("#tip-period").html('第1年1期');

            };

            setdata();

        }
    });




    $("#apList").change(function () {

        var apText = $("#apList").find("option:selected").text();
        var currentAp =$("#apList").find("option:selected").val();
        $("#currentAp").val(currentAp);
        setdata();
        $("#tip-period").html(apText);

    });


    $("#main-panel").on('input propertychange', function()
    {
        savedata();


    });

    $('#pop-clear').click(function () {
        $("#main-panel input").each(
            function () {
                $(this).val('');
            }
        );
    });

    $('#pop-rule').click(function () {
        window.open("/SandtableManual/downfile?fileName=rule-manual.pdf");
    });

    $('#pop-download').click(function () {
        window.open("/SandtableManual/downfile?fileName=worksheet.xlsx");
    });


})

function savedata() {
    var currentUser =  $("#currentUser").val();
    var currentAp =  $("#currentAp").val() ;
    var userData = '';


    $('#main-panel input').each(function () {
        var myvalue = $(this).val();
        if( myvalue != '')
        {userData =userData+$(this).val()+'|' ;}
        else{userData =userData+'null|'};
    })


    console.log(userData);

    var myJson = { userId :currentUser,period :currentAp,userData :userData};
    /* 提交当前经营期间的数据*/
    $.ajax({
        type: "post",
        dataType: "json",
        data: JSON.stringify(myJson),
        url: "/SandtableManual/add",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {

        }
    })
}

function setdata() {



    var currentUser =  $("#currentUser").val();
    var currentAp =  $("#currentAp").val() ;
    var myJson = { userId :currentUser,period :currentAp};


    /* 获取当前经营期间的数据*/
    $.ajax({
        type: "post",
        dataType: "json",
        data: JSON.stringify(myJson),
        url: "/SandtableManual/listbyuserandperiod",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            var userData = data['paneldata'];
            console.log(data);
            if(userData != null && userData != '')
            {

                var strs = new Array(); //定义一数组
                strs = userData.split("|"); //字符分割
                var  i = 0;

                $("#main-panel input").each(
                    function () {
                        if(strs[i] != 'null')
                        {
                            $(this).val(strs[i]);
                        }
                        else
                        {
                            $(this).val('');
                        }

                        i++;

                    }
                )
            }else
            {

                $("#main-panel input").each(
                    function () {
                        $(this).val('');

                    }
                );
                alert('所选期间无数据');
            }




        }
    })

}