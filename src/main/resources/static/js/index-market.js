$(document).ready(function () {


    //已获取的订单弹窗-P1
    $('#p1-list').click(function () {
        $('.pop-p1').show();
        var myquery1 = { productId : "P1"} ;
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(myquery1),
            url: "/OrderManagement/listCurrentPeriodOrder",
            contentType: "application/json;charset=utf-8;",
            success:function(data){
                data = data['data'];
                console.log(data);


                var tableStr = "<table class='table'>";
                tableStr = tableStr
                    + "<tr>"
                    +"<td >编号</td>" +"<td >产品</td>" +"<td >数量</td>" +"<td >金额</td>"+"<td >账期</td>"
                    +"<td >操作</td>"+"</tr>";
                var len = data.length;
                var lenP1=0;
                for ( var i = 0; i < len; i++) {
                    if(data[i].productId=="P1") {
                        lenP1++;
                        tableStr = tableStr + "<tr>"
                            + "<td>" + data[i].orderId + "</td>"
                            + "<td>" + data[i].productId + "</td>"
                            + "<td>" + data[i].amount + "</td>"
                            + "<td>" + data[i].money + "</td>"
                            + "<td>" + data[i].periodPay + "</td>"
                            + "<td ><input type='button' class='btn  btn-sm  btn-danger '  onclick='' value='交货'></td>"
                            + "</tr>";
                    }
                }


                if(lenP1==0){
                    tableStr = tableStr + "<tr style='text-align: center'>"
                        +"<td colspan='6'><font color='#cd0a0a'>"+ '无订单' + "</font></td>"
                        +"</tr>";
                }


                tableStr = tableStr + "</table>";
                //添加到div中
                $("#ordermanagement1").html(tableStr);
                $("#ordermanagement1").delegate("input", "click",
                    function(e) {
                        var myOrderName = "xxx";
                        myOrderId = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                        console.log(myOrderName);
                        var myJson = { orderId : myOrderId} ;

                        $.ajax({
                            type: "post",
                            dataType: "json",
                            data: JSON.stringify(myJson),
                            url: "/OrderManagement/stockout",
                            contentType: "application/json;charset=utf-8;",
                            success: function (data) {
                                alert("交货成功");
                                window.location.href = "/index";
                            }
                        });

                    });
            }
        })

    });


    //已获取的订单弹窗-P2
    $('#p2-list').click(function () {
        $('.pop-p2').show();
        var myquery1 = { productId : "P2"} ;

        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(myquery1),
            url: "/OrderManagement/listCurrentPeriodOrder",
            contentType: "application/json;charset=utf-8;",
            success:function(data){
                data = data['data'];
                console.log(data);
                var tableStr = "<table class='table'>";
                tableStr = tableStr
                    + "<tr>"
                    +"<td >编号</td>" +"<td >产品</td>" +"<td >数量</td>" +"<td >金额</td>"+"<td >账期</td>"
                    +"<td >操作</td>"+"</tr>";
                var len = data.length;
                var lenP2=0;
                for ( var i = 0; i < len; i++) {
                    if (data[i].productId == "P2") {
                        lenP2++;
                        tableStr = tableStr + "<tr>"
                            + "<td>" + data[i].orderId + "</td>"
                            + "<td>" + data[i].productId + "</td>"
                            + "<td>" + data[i].amount + "</td>"
                            + "<td>" + data[i].money + "</td>"
                            + "<td>" + data[i].periodPay + "</td>"
                            + "<td ><input type='button' class='btn   btn-sm btn-danger '  onclick='' value='交货'></td>"
                            + "</tr>";
                    }
                }
                if(lenP2==0){
                    tableStr = tableStr + "<tr style='text-align: center'>"
                        +"<td colspan='6'><font color='#cd0a0a'>"+ '无订单' + "</font></td>"
                        +"</tr>";
                }
                tableStr = tableStr + "</table>";
                //添加到div中
                $("#ordermanagement2").html(tableStr);
                $("#ordermanagement2").delegate("input", "click",
                    function(e) {
                        var myOrderName = "xxx";
                        myOrderId = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                        console.log(myOrderName);
                        var myJson = { orderId : myOrderId} ;

                        $.ajax({
                            type: "post",
                            dataType: "json",
                            data: JSON.stringify(myJson),
                            url: "/OrderManagement/stockout",
                            contentType: "application/json;charset=utf-8;",
                            success: function (data) {
                                alert("交货成功");
                                window.location.href = "/index";
                            }
                        });

                    });
            }
        })

    });

    //已获取的订单弹窗-P3
    $('#p3-list').click(function () {
        $('.pop-p3').show();
        var myquery1 = { productId : "P3"} ;
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(myquery1),
            url: "/OrderManagement/listCurrentPeriodOrder",
            contentType: "application/json;charset=utf-8;",
            success:function(data){
                data = data['data'];
                console.log(data);
                var tableStr = "<table border='0' cellspacing='' cellpadding=''>";
                tableStr = tableStr
                    + "<tr>"
                    +"<td >编号</td>" +"<td >产品</td>" +"<td >数量</td>" +"<td >金额</td>"+"<td >账期</td>"
                    +"<td >操作</td>"+"</tr>";
                var len = data.length;
                var lenP3 =0;
                for ( var i = 0; i < len; i++) {
                    if(data[i].productId=="P3"){
                        lenP3++;
                    tableStr = tableStr + "<tr>"
                        +"<td>"+ data[i].orderId + "</td>"
                        + "<td>"+ data[i].productId + "</td>"
                        + "<td>"+ data[i].amount + "</td>"
                        +"<td>"+data[i].money+"</td>"
                        + "<td>" + data[i].periodPay + "</td>"
                        +"<td ><input type='button' class='btn  btn-sm  btn-danger '  onclick=''  value='交货'></td>"
                        +"</tr>";
                }}
                if(lenP3==0){
                    tableStr = tableStr + "<tr style='text-align: center'>"
                        +"<td colspan='6'><font color='#cd0a0a'>"+ '无订单' + "</font></td>"
                        +"</tr>";
                }
                tableStr = tableStr + "</table>";
                //添加到div中
                $("#ordermanagement3").html(tableStr);
                $("#ordermanagement3").delegate("input", "click",
                    function(e) {
                        var myOrderName = "xxx";
                        myOrderId = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                        console.log(myOrderName);
                        var myJson = { orderId : myOrderId} ;

                        $.ajax({
                            type: "post",
                            dataType: "json",
                            data: JSON.stringify(myJson),
                            url: "/OrderManagement/stockout",
                            contentType: "application/json;charset=utf-8;",
                            success: function (data) {
                                alert("交货成功");
                                window.location.href = "/index";
                            }
                        });

                    });
            }
        })

    });

    //已获取的订单弹窗-P4
    $('#p4-list').click(function () {
        $('.pop-p4').show();
        var myquery1 = { productId : "P4"} ;
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(myquery1),
            url: "/OrderManagement/listCurrentPeriodOrder",
            contentType: "application/json;charset=utf-8;",
            success:function(data){
                data = data['data'];
                console.log(data);
                var tableStr = "<table border='0' cellspacing='' cellpadding=''>";
                tableStr = tableStr
                    + "<tr>"
                    +"<td >编号</td>" +"<td >产品</td>" +"<td >数量</td>" +"<td >金额</td>"+"<td >账期</td>"
                    +"<td >操作</td>"+"</tr>";
                var len = data.length;
                var lenP4=0;
                for ( var i = 0; i < len; i++) {
                    if (data[i].productId == "P4") {
                        lenP4++;
                        tableStr = tableStr + "<tr>"
                            + "<td>" + data[i].orderId + "</td>"
                            + "<td>" + data[i].productId + "</td>"
                            + "<td>" + data[i].amount + "</td>"
                            + "<td>" + data[i].money + "</td>"
                            + "<td>" + data[i].periodPay + "</td>"
                            + "<td ><input type='button' class='btn  btn-sm   btn-danger '  onclick=''  value='交货'></td>"
                            + "</tr>";
                    }
                }
                if(lenP4==0){
                    tableStr = tableStr + "<tr style='text-align: center'>"
                        +"<td colspan='6'><font color='#cd0a0a'>"+ '无订单' + "</font></td>"
                        +"</tr>";
                }
                tableStr = tableStr + "</table>";
                //添加到div中
                $("#ordermanagement4").html(tableStr);
                $("#ordermanagement4").delegate("input", "click",
                    function(e) {
                        var myOrderName = "xxx";
                        myOrderId = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                        console.log(myOrderName);
                        var myJson = { orderId : myOrderId} ;

                        $.ajax({
                            type: "post",
                            dataType: "json",
                            data: JSON.stringify(myJson),
                            url: "/OrderManagement/stockout",
                            contentType: "application/json;charset=utf-8;",
                            success: function (data) {
                                alert("交货成功");
                                window.location.href = "/index";
                            }
                        });

                    });
            }
        })

    });


})