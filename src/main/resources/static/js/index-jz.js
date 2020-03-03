$(document).ready(function () {

 /*   //初始化公司或群组的数据*/
    $("#reloaddata").click(function () {
        var currentAp = $("#currentAp").val();
        var currentTeam = $("#currentTeam").val();
        var mydata ={userTeam:currentTeam,period:currentAp};
   /*     //初始化数据到指定的小组或是公司*/
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
        window.location.reload();
    });


    $("#closing").click(function () {
        var currentAp = $("#currentAp").val();
        var currentTeam = $("#currentTeam").val();
        var mydata ={userTeam:currentTeam,period:currentAp};
        //会计结账处理
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/closing",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(mydata),
            success: function (data) {
                var myMsg = data['msg'];
                console.log(myMsg);
                alert(data['msg']);
            }
        })
        window.location.reload();/*//页面刷新*/
    });


/*    //与教育部平台对接时，提交成绩*/
    $("#final").click(function () {


        var myEndPoint = new Date();
        var myEndPointH = myEndPoint.getHours();
        var myEndPointM = myEndPoint.getMinutes();
        var myStartH =   $("#startPointH").val();
        var myStartM =  $("#startPointM").val();
        var myTestTime = 0;
        var myExpSore = 0;
        var myTestTime =  Number(myEndPointH)*60+Number(myEndPointM)-Number(myStartH)*60-Number(myStartM);

        if(  myTestTime <= 5 )
        {  myExpSore = Math.floor(Math.random() * (60 - 1)) + 1;}
        if(  myTestTime > 5 &&  myTestTime <= 20)
        {  myExpSore = Math.floor(Math.random() * (80 - 60)) + 60;}
        if(  myTestTime > 20 )
        {  myExpSore = Math.floor(Math.random() * (95 - 80)) + 80;}



        var myeId =  $("#eId").val();
        var param='{"eid":"'+myeId+'","expScore":"'+myExpSore+'"}';

        $.ajax({
            url:"http://139.224.197.21:8081/openlab/outer/intelligent/!expScoreSave",    //请求的url地址
            dataType:"json",   //返回格式为json
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"param":param},    //参数值
            type:"POST",   //请求方式
            success:function(req){
                console.log(req)
                //请求成功时处理
            }
        });


    });


})