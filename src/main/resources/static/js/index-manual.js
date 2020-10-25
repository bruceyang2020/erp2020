
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
                $("#currentAp").val("0");
                $("#tip-period").html('第0年4期');
                $("#apList").val("0").select2();
                $("#tip-ilabusername").html(data.currentIlabName);

            }

            setdata();

        }
    });




    $("#apList").change(function () {

        var apText =$("#apList").find("option:selected").text();
        var currentAp =$("#apList").find("option:selected").val();
        $("#currentAp").val(currentAp);
        setdata();
        $("#tip-period").html(apText);

    });


    $("#main-panel").on('input propertychange', function()
    {
        savedata();


    });

    $('#pop-prior').click(function () {
        var currentUser =  $("#currentUser").val();
        var currentAp =  $("#currentAp").val() ;
        currentAp = Number(currentAp)-1;
        if(Number(currentAp) < 0)
        {
            alert('已到起始期');
        }else
        {
            $("#currentAp").val(currentAp) ;
            $("#apList").val(currentAp).select2();
            var apText = $("#apList").find("option:selected").text();
            setdata();
            $("#tip-period").html(apText);

        }



    });


    $('#pop-next').click(function () {

        var currentUser =  $("#currentUser").val();
        var currentAp =  $("#currentAp").val() ;
        currentAp = Number(currentAp)+1;
        console.log('触发下一期');
        if(Number(currentAp) > 24)
        {
            alert('已到最后一期');
        }else
        {
            $("#currentAp").val(currentAp) ;
            $("#apList").val(currentAp).select2();
            var apText = $("#apList").find("option:selected").text();
            setdata();
            $("#tip-period").html(apText);

        }

    });

    $('#pop-clear').click(function () {


        var currentUser =  $("#currentUser").val();
        var currentAp =  $("#currentAp").val() ;
        var myJson = { userId :currentUser,period :currentAp};



        /* 删除当前期间的数据，加载上一期的期末数据*/
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(myJson),
            url: "/SandtableManual/reloadByUserIdAndPeriod",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {
               /* $("#main-panel input").each(
                    function () {
                        $(this).val('');
                    }
                );*/
                setdata();
            }
        })


    });

    $('#pop-rule').click(function () {
        window.open("/SandtableManual/downfile?fileName=rule-manual.pdf");
    });

    $('#pop-download').click(function () {
        window.open("/SandtableManual/downfile?fileName=worksheet.xlsx");
    });



    $("#pop-newpage").click(function () {
        window.open("/indexmanual");
    });


    $("#pop-backhome").click(function () {
        window.location.href = "/optionList";
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
                        $(this).css({'font-weight':'bold','font-size':'20px','color':'dodgerblue','text-align': 'center'});

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