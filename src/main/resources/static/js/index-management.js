$(document).ready(function () {

    //原材料及成品库存显示-Inv
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/Inv/listInv",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            console.log(data);
            $.each(data,function(n,value){
                if(value.number == "R1")
                {  $("#mag-r4").text(value.amountB+value.amountI-value.amountO);}
                if(value.number == "R2")
                {  $("#mag-r3").text(value.amountB+value.amountI-value.amountO);}
                if(value.number == "R3")
                {  $("#mag-r2").text(value.amountB+value.amountI-value.amountO);}
                if(value.number == "R4")
                {  $("#mag-r1").text(value.amountB+value.amountI-value.amountO);}
                if(value.number == "P1")
                {  $("#mag-p1").text(value.amountB+value.amountI-value.amountO);}
                if(value.number == "P2")
                {  $("#mag-p2").text(value.amountB+value.amountI-value.amountO);}
                if(value.number == "P3")
                {  $("#mag-p3").text(value.amountB+value.amountI-value.amountO);}
                if(value.number == "P4")
                {  $("#mag-p4").text(value.amountB+value.amountI-value.amountO);}

            });
            console.log('************类型1原材料数据');
        }
    });



    //采购订单的显示--MaterialOrder
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/MaterialOrder/list",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            console.log(data);
            $.each(data,function(n,value){
                var currentAp =$("#currentAp").val();
                var leadTime = 1+currentAp*1-value.period;
                console.log(leadTime);
                if(value.materialId == "R1" && leadTime == 1 )
                {  $("#r1-1").val(value.amount);}
                if(value.materialId == "R2" && leadTime == 1)
                {  $("#r2-1").val(value.amount);}
                if(value.materialId == "R3"  && leadTime == 1)
                {  $("#r3-2").val(value.amount);}
                if(value.materialId == "R3"  && leadTime == 2)
                {  $("#r3-1").val(value.amount);}
                if(value.materialId == "R4" && leadTime == 1)
                {  $("#r4-2").val(value.amount);}
                if(value.materialId == "R4" && leadTime == 2)
                {  $("#r4-1").val(value.amount);}

            });
            console.log('采购订单提取数据成功');
        }
    });


    //原材料采购的点击事件：R1、R2、R3、R4
    $("#ok-r1-1").click(function () {
        var amount = $('#buy-r1-1').val();
        if(amount<0||amount==null||amount%1!=0){
            alert("请输入正确的数值");
            return;
        }
        var MaterialOrder = {
            period: $('#currentAp').val(),
            amount: amount,
            moneyTotal:$('#buy-r1-1').val(),
            materialId:"R1"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MaterialOrder/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MaterialOrder),
            success: function (data) {
                $("#r1-1").val(amount);
                alert("RI订单成功");
            }
        })
    })

    $("#ok-r2-1").click(function () {
        var amount = $('#buy-r2-1').val();
        if(amount<0||amount==null||amount%1!=0){
            alert("请输入正确的数值");
            return;
        }
        var MaterialOrder = {
            period: $('#currentAp').val(),
            amount: amount,
            moneyTotal:$('#buy-r2-1').val(),
            materialId:"R2"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MaterialOrder/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MaterialOrder),
            success: function (data) {
                $("#r2-1").val(amount);
                alert("R2订单成功");
            }
        })
    })

    $("#ok-r3-2").click(function () {
        var amount = $('#buy-r3-2').val();
        if(amount<0||amount==null||amount%1!=0){
            alert("请输入正确的数值");
            return;
        }
        var MaterialOrder = {
            period: $('#currentAp').val(),
            amount: amount,
            moneyTotal:$('#buy-r3-2').val(),
            materialId:"R3"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MaterialOrder/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MaterialOrder),
            success: function (data) {
                $("#r3-2").val(amount);
                alert("R3订单成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("400"));//ISO2扣400
        touzi = parseInt(touzi) + parseInt("400");//记录扣款数
    })

    $("#ok-r4-2").click(function () {
        var amount = $('#buy-r4-2').val();
        if(amount<0||amount==null||amount%1!=0){
            alert("请输入正确的数值");
            return;
        }
        var MaterialOrder = {
            period: $('#currentAp').val(),
            amount:amount,
            moneyTotal:$('#buy-r4-2').val(),
            materialId:"R4"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MaterialOrder/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MaterialOrder),
            success: function (data) {
                $("#r4-2").val(amount);
                alert("R4订单成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("400"));//ISO2扣400
        touzi = parseInt(touzi) + parseInt("400");//记录扣款数
    })

})