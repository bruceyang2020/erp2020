/**
H 完成手工沙盘订单的一系列操作
**/

$(document).ready(function () {

    //组别的弹窗
    $('#pop-window').click(function () {
        $('.pop-order').show();
    })
    $('.pop-cancel').click(function () {
        $('.pop-order').hide();
    })


    //H 生成订单
    $("#pop-create-order").click(function () {

        //GroupId 为随机数 一个大写字母和两个数字和一个小写字母
        var groupId;
        groupId = String.fromCharCode(
            Math.floor(Math.random()*26)+"A".charCodeAt(0),
            Math.floor(Math.random()*9)+48,
            Math.floor(Math.random()*9)+48,
            Math.floor(Math.random()*26)+"a".charCodeAt(0),
            )

        var sandtableOrder = {
            groupId: groupId
        };
        alert(groupId);
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/SandtableOrder/adds",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(sandtableOrder),
            success: function (data) {
                alert("ok");
            }
        })
    })


    //H 订单显示
    $('#pop-group').click(function () {
        let currentGp = $("#group-name").val();
        $("#currentGroup").val(currentGp);
        let currentUser = $("#currentUser").val();
        let currentAp = $("#currentAp").val();
        let a = Math.floor((parseInt(currentAp)-1) / 4);
        let period = (a+ 1)*4-3;
        let groupOrder = {
            period: period,
            groupId: currentGp,
            userId: currentUser
        }

        //选单期间
         if (parseInt(currentAp) == parseInt(period)) {
            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(groupOrder),
                url: "/SandtableOrder/checkByGroupId",
                contentType: "application/json;charset=utf-8;",
                success: function (data) {
                    data = data['data'];
                    console.log(data)
                    var tableStr = "<table style ='align-self: center;width: 480px;table-layout:fixed;word-break:break-all;border-collapse:collapse;border: #0e1e2f 2px;text-align: center;'>";
                    tableStr = tableStr
                        + "<tr>"
                        + "<th style='width: 12%'>编号</th>" + "<th style='width: 10%'>组号</th>" + "<th style='width: 10%'>产品</th>" + "<th style='width: 10%'>市场</th>" + "<th style='width: 5%'>数量</th>"
                        + "<th style='width: 5%' >金额</th>" + "<th style='width: 8%'>ISO认证</th>" + "<th style='width: 5%'>加急</th>" + "<th style='width: 5%'>账期</th>" + "<th >中标人</th>" + "<th style='width: 15%'>操作</th>" + "</tr>";
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        tableStr = tableStr + "<tr style='border-top: 1px solid #0e1e2f;border-bottom: 1px solid #0e1e2f;border-right: 1px solid #0e1e2f;border-left: 1px solid #0e1e2f'>"
                            + "<td >" + data[i].orderId + "</td>"
                            + "<td>" + data[i].groupId + "</td>"
                            + "<td>" + data[i].productId + "</td>"
                            + "<td>" + data[i].marketId + "</td>"
                            + "<td>" + data[i].amount + "</td>"
                            + "<td>" + data[i].priceTotal + "</td>"
                            + "<td>" + data[i].iso + "</td>"
                            + "<td>" + data[i].isurgent + "</td>"
                            + "<td>" + data[i].periodPay + "</td>"
                            + "<td>" + data[i].userId + "</td>";
                        if (data[i].userId == " ") {
                            tableStr = tableStr + "<td ><input type='button' style=' padding:5px 15px;  border: none;  border-radius: 5px;  background-color: #666699;  color: #fff;  cursor:pointer; ' class='pop-ok' onclick='' value='选取'></td>"
                                + "</tr>";
                        } else {
                            tableStr = tableStr + "<td ><input type='button' style=' padding:5px 15px;  border: none;  border-radius: 5px;  background-color: #666699;  color: #fff;  cursor:pointer; ' class='pop-cancel pop-close ' onclick='' value='取消'></td>"
                                + "</tr>";
                        }
                    }
                    tableStr = tableStr + "</table>";
                    $("#sandtable-order-management").html(tableStr);
               }
            });
         }

         //非选单期间
        else {
            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(groupOrder),
                url: "/SandtableOrder/checkByGroupIdAndUserId",
                contentType: "application/json;charset=utf-8;",
                success: function (data) {
                    data = data['data'];
                    console.log(data)
                    var tableStr = "<table style ='width: 480px;table-layout:fixed;word-break:break-all;border-collapse:collapse;border: #0e1e2f 2px;text-align: center;'>";
                    tableStr = tableStr
                        + "<tr >"
                        + "<th style='width: 12%'>编号</th>" + "<th style='width: 10%'>组号</th>" + "<th style='width: 10%'>产品</th>" + "<th style='width: 12%'>市场</th>" + "<th style='width: 5%'>数量</th>"
                        + "<th style='width: 5%'>金额</th>" + "<th style='width: 8%'>ISO认证</th>" + "<th style='width: 5%'>加急</th>" + "<th style='width: 5%'>账期</th>" + "<th >中标人</th>" + "</tr>";
                    var len = data.length;
                    for (var i = 0; i < len; i++) {
                        tableStr = tableStr + "<tr style='border-top: 1px solid #0e1e2f;border-bottom: 1px solid #0e1e2f;border-right: 1px solid #0e1e2f;border-left: 1px solid #0e1e2f'>"
                            + "<td>" + data[i].orderId + "</td>"
                            + "<td>" + data[i].groupId + "</td>"
                            + "<td>" + data[i].productId + "</td>"
                            + "<td>" + data[i].marketId + "</td>"
                            + "<td>" + data[i].amount + "</td>"
                            + "<td>" + data[i].priceTotal + "</td>"
                            + "<td>" + data[i].iso + "</td>"
                            + "<td>" + data[i].isurgent + "</td>"
                            + "<td>" + data[i].periodPay + "</td>"
                            + "<td>" + data[i].userId + "</td>"
                            + "</tr>";
                    }
                    tableStr = tableStr + "</table>";
                    $("#sandtable-order-management").html(tableStr);

                }
            });
        }

        //添加监视
        listen(groupOrder);
    });
})


function listen(groupOrder) {
    $("#sandtable-order-management").undelegate();
    $("#sandtable-order-management").delegate("input", "click",
        function (e) {
            myOrderId = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
            state = $(e.currentTarget).val();
            var p = $(e.currentTarget);
            var myJson = {
                orderId: myOrderId,
                groupId: groupOrder.groupId,
                userId: groupOrder.userId,
                period: groupOrder.period
            };

            //选取订单
            if (state == "选取") {
                // select(myJson);
                $.ajax({
                    type: "post",
                    dataType: "json",
                    data: JSON.stringify(myJson),
                    url: "/SandtableOrder/add",
                    contentType: "application/json;charset=utf-8;",
                    success: function (data) {
                        alert(data['msg']);
                        p.val("取消");
                        p.parent("td").parent("tr").children("td")[9].innerText = groupOrder.userId;
                    }
                });
                $("#sandtable-order-management").undelegate();
            }

            //取消选取
            else if (state == "取消") {
                if(p.parent("td").parent("tr").children("td")[9].innerText != groupOrder.userId){
                    alert("非中标人不得取消选单！")
                }
                else {
                    // deselect(myJson);
                    $.ajax({
                        type: "post",
                        dataType: "json",
                        data: JSON.stringify(myJson),
                        url: "/SandtableOrder/del",
                        contentType: "application/json;charset=utf-8;",
                        success: function (data) {
                            var myMsg = data['msg'];
                            alert(data['msg']);
                            p.val("选取");
                            p.parent("td").parent("tr").children("td")[9].innerText = " ";

                        }
                    });
                    $("#sandtable-order-management").undelegate();
                }
            }
        })
}

//选单
function select(myJson){
    $.ajax({
        type: "post",
        dataType: "json",
        data: JSON.stringify(myJson),
        url: "/SandtableOrder/add",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            alert(data['msg']);
        }
    });
}

//取消选单
function deselect(myJson){
    $.ajax({
        type: "post",
        dataType: "json",
        data: JSON.stringify(myJson),
        url: "/SandtableOrder/del",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            var myMsg = data['msg'];
            alert(data['msg']);
        }
    });
}



