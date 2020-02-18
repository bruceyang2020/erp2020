$(document).ready(function () {

    //初始化公司或群组的数据
    $("#reloaddata").click(function () {
        var currentAp = $("#currentAp").val();
        var currentTeam = $("#currentTeam").val();
        var mydata ={userTeam:currentTeam,period:currentAp};
        //初始化数据到指定的小组或是公司
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/reloaddata",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(mydata),
            success: function (data) {
                var myMsg = data['msg'];
                console.log(myMsg);
                alert(data['msg']);
            }
        })
        window.location.reload();//页面刷新
    });


    $("#Accountingclosing").click(function () {
        var currentAp = $("#currentAp").val();
        var currentTeam = $("#currentTeam").val();
        var mydata ={userTeam:currentTeam,period:currentAp};
        //会计结账处理
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/Index/closing",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(mydata),
            success: function (data) {
                var myMsg = data['msg'];
                console.log(myMsg);
                alert(data['msg']);
            }
        })
        window.location.reload();//页面刷新
    });


    //与教育部平台对接时，提交成绩
    $("#final").click(function () {

        //提交成绩到教育部平台上。暂不处理
        window.location.reload();//页面刷新
    });


})