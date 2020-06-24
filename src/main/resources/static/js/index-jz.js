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
                window.location.href = "/index";

            }
        })


    });


    $("#closing").click(function () {
        var currentAp = $("#currentAp").val();
        var currentTeam = $("#currentTeam").val();
        var mydata ={userTeam:currentTeam,period:currentAp};

        $('.jz-timeg').addClass('show');
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/closing?tm="+new Date().getTime(),
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(mydata),
            success: function (data) {
                var myMsg = data['msg'];
                window.location.href = "/index";

            }
        });

        updatatest();




    });


/*    //与教育部平台对接时，提交成绩*/
    $("#final").click(function () {

         updatatest();



    });

/*显示经营规则*/
    $("#showrule").click(function () {
        updatatest();
        $('.pop-jz').hide();
        $('.pop-rule').show();

    });

    function updatatest() {


        var myEndPoint = new Date();
        var myEndPointH = myEndPoint.getHours();
        var myEndPointM = myEndPoint.getMinutes();
        var myStartH =   $("#startPointH").val();
        var myStartM =  $("#startPointM").val();
        var myTestTime = 0;
        var myExpSore = 0;
        var myTestTime =  Number(myEndPointH)*60+Number(myEndPointM)-Number(myStartH)*60-Number(myStartM);

        if(  myTestTime <= 1 )
        {  myExpSore = Math.floor(Math.random() * (80 - 60)) + 60;}
        if(  myTestTime > 1 &&  myTestTime <= 5)
        {  myExpSore = Math.floor(Math.random() * (90 - 80)) + 80;}
        if(  myTestTime > 5 )
        {  myExpSore = Math.floor(Math.random() * (95 - 90)) + 90;}



        $.ajax({
            type: "post",
            dataType: "json",
            url: "/SysUser/getCurrentScore",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {
                data = data['data'];

                myExpSore = data['score'];
                var myeId =  $("#eId").val();
                var param='{"eid":"'+myeId+'","expScore":"'+myExpSore+'"}';



                $.ajax({
                    url:"http://139.224.197.21:8081/openlab/outer/intelligent/!expScoreSave",
                    dataType:"json",
                    async:true,
                    data:{"param":param},
                    type:"POST",
                    success:function(req){
                        console.log("当前用户eid："+myeId);
                        console.log("当前用户成绩："+myExpSore);
                        console.log(req)

                    }
                });

            }
        })



    }


})