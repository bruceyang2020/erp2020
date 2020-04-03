

$(function(){
    $('#tabs a').click(function(e) {
        e.preventDefault();
        $('#tabs li').removeClass("current").removeClass("hoverItem");
        $(this).parent().addClass("current");
        $("#pop-content div").removeClass("show");
        $('#' + $(this).attr('title')).addClass('show');
        // alert($(this).attr('title'));
        if($(this).attr('title')=="tab1"){

            var myquery1 = { productId : "P1"} ;
            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(myquery1),
                url: "/OrderGroup/list",
                contentType: "application/json;charset=utf-8;",
                success:function(data){
                    data = data['data'];
                    console.log(data);
                    var tableStr = "<table border='0' cellspacing='' cellpadding=''>";
                    tableStr = tableStr
                        + "<tr>"
                        +"<td >编号</td>" +"<td >市场</td>" +"<td >数量</td>" +"<td >金额</td>" +"<td >账期</td>" +"<td >加急</td>"+"<td >认证</td>"+"<td >状态</td>"
                        +"<td >操作</td>"+"</tr>";
                    var len = data.length;
                    for ( var i = 0; i < len; i++) {
                        tableStr = tableStr + "<tr>"
                            +"<td>"+ data[i].orderName + "</td>"
                            + "<td>"+ data[i].marketId + "</td>"
                            + "<td>"+ data[i].amount + "</td>"
                            +"<td>"+data[i].priceTotal+"</td>"
                            +"<td class='purple'>"+data[i].periodPay+"</td>"
                            +"<td >"+data[i].isurgent+"</td>"
                            +"<td >"+data[i].iso+"</td>"
                            +"<td >"+"备选"+"</td>"
                            +"<td ><button type='button' class='btn  btn-mini btn-select  btn-p1'  onclick=''>选择</button></td>"
                            +"</tr>";
                    }
                    if(len==0){
                        tableStr = tableStr + "<tr style='text-align: center'>"
                            +"<td colspan='6'><font color='#cd0a0a'>"+ '无可选的订单' + "</font></td>"
                            +"</tr>";
                    }
                    tableStr = tableStr + "</table>";
                    //添加到div中
                    $("#listP1").html(tableStr);

                    $("#listP1").delegate("button.btn-p1", "click",
                        function(e) {


                            var myOrderName = "xxx";
                            myOrderName = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                            console.log(myOrderName);
                            var myJson = { orderId : myOrderName} ;

                            $.ajax({
                                type: "post",
                                dataType: "json",
                                data: JSON.stringify(myJson),
                                url: "/OrderManagement/add",
                                contentType: "application/json;charset=utf-8;",
                                success: function (data) {
                                    alert("选单成功");
                                }
                            });

                        });
                }
            })
        }
        if($(this).attr('title')=="tab2"){
            var myquery1 = { productId : "P2"} ;
            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(myquery1),
                url: "/OrderGroup/list",
                contentType: "application/json;charset=utf-8;",
                success:function(data){
                    data = data['data'];
                    console.log(data);
                    var tableStr = "<table border='0' cellspacing='' cellpadding=''>";
                    tableStr = tableStr
                        + "<tr>"
                        +"<td >编号</td>" +"<td >市场</td>" +"<td >数量</td>" +"<td >金额</td>" +"<td >账期</td>" +"<td >加急</td>"+"<td >认证</td>"+"<td >状态</td>"
                        +"<td >操作</td>"+"</tr>";
                    var len = data.length;
                    for ( var i = 0; i < len; i++) {
                        tableStr = tableStr + "<tr>"
                            +"<td>"+ data[i].orderName + "</td>"
                            + "<td>"+ data[i].marketId + "</td>"
                            + "<td>"+ data[i].amount + "</td>"
                            +"<td>"+data[i].priceTotal+"</td>"
                            +"<td class='purple'>"+data[i].periodPay+"</td>"
                            +"<td >"+data[i].isurgent+"</td>"
                            +"<td >"+data[i].iso+"</td>"
                            +"<td >"+"备选"+"</td>"
                            +"<td ><button type='button' class='btn  btn-mini btn-select btn-p2'  onclick=''>选择</button></td>"
                            +"</tr>";
                    }
                    if(len==0){
                        tableStr = tableStr + "<tr style='text-align: center'>"
                            +"<td colspan='6'><font color='#cd0a0a'>"+ '无可选的订单' + "</font></td>"
                            +"</tr>";
                    }
                    tableStr = tableStr + "</table>";
                    //添加到div中
                    $("#listP2").html(tableStr);

                    $("#listP2").delegate("button.btn-p2", "click",
                        function(e) {
                            var myOrderName = "xxx";
                            myOrderName = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                            console.log(myOrderName);
                            var myJson = { orderId : myOrderName} ;

                            $.ajax({
                                type: "post",
                                dataType: "json",
                                data: JSON.stringify(myJson),
                                url: "/OrderManagement/add",
                                contentType: "application/json;charset=utf-8;",
                                success: function (data) {
                                    alert("选单成功");
                                }
                            });

                        });
                }
            })
        }
        if($(this).attr('title')=="tab3"){
            var myquery1 = { productId : "P3"} ;
            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(myquery1),
                url: "/OrderGroup/list",
                contentType: "application/json;charset=utf-8;",
                success:function(data){
                    data = data['data'];
                    console.log(data);
                    var tableStr = "<table border='0' cellspacing='' cellpadding=''>";
                    tableStr = tableStr
                        + "<tr>"
                        +"<td >编号</td>" +"<td >市场</td>" +"<td >数量</td>" +"<td >金额</td>" +"<td >账期</td>" +"<td >加急</td>"+"<td >认证</td>"+"<td >状态</td>"
                        +"<td >操作</td>"+"</tr>";
                    var len = data.length;
                    for ( var i = 0; i < len; i++) {
                        tableStr = tableStr + "<tr>"
                            +"<td>"+ data[i].orderName + "</td>"
                            + "<td>"+ data[i].marketId + "</td>"
                            + "<td>"+ data[i].amount + "</td>"
                            +"<td>"+data[i].priceTotal+"</td>"
                            +"<td class='purple'>"+data[i].periodPay+"</td>"
                            +"<td >"+data[i].isurgent+"</td>"
                            +"<td >"+data[i].iso+"</td>"
                            +"<td >"+"备选"+"</td>"
                            +"<td ><button type='button' class='btn  btn-mini btn-select btn-p3'  onclick=''>选择</button></td>"
                            +"</tr>";
                    }
                    if(len==0){
                        tableStr = tableStr + "<tr style='text-align: center'>"
                            +"<td colspan='6'><font color='#cd0a0a'>"+ '无可选的订单' + "</font></td>"
                            +"</tr>";
                    }
                    tableStr = tableStr + "</table>";
                    //添加到div中
                    $("#listP3").html(tableStr);
                    $("#listP3").delegate("button.btn-p3", "click",
                        function(e) {
                            var myOrderName = "xxx";
                            myOrderName = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                            console.log(myOrderName);
                            var myJson = { orderId : myOrderName} ;

                            $.ajax({
                                type: "post",
                                dataType: "json",
                                data: JSON.stringify(myJson),
                                url: "/OrderManagement/add",
                                contentType: "application/json;charset=utf-8;",
                                success: function (data) {
                                    alert("选单成功");
                                }
                            });

                        });
                }
            })
        }

        if($(this).attr('title')=="tab4"){
            var myquery1 = { productId : "P4"} ;
            $.ajax({
                type: "post",
                dataType: "json",
                data: JSON.stringify(myquery1),
                url: "/OrderGroup/list",
                contentType: "application/json;charset=utf-8;",
                success:function(data){
                    data = data['data'];
                    console.log(data);
                    var tableStr = "<table border='0' cellspacing='' cellpadding=''>";
                    tableStr = tableStr
                        + "<tr>"
                        +"<td >编号</td>" +"<td >市场</td>" +"<td >数量</td>" +"<td >金额</td>" +"<td >账期</td>" +"<td >加急</td>"+"<td >认证</td>"+"<td >状态</td>"
                        +"<td >操作</td>"+"</tr>";
                    var len = data.length;
                    for ( var i = 0; i < len; i++) {
                        tableStr = tableStr + "<tr>"
                            +"<td>"+ data[i].orderName + "</td>"
                            + "<td>"+ data[i].marketId + "</td>"
                            + "<td>"+ data[i].amount + "</td>"
                            +"<td>"+data[i].priceTotal+"</td>"
                            +"<td class='purple'>"+data[i].periodPay+"</td>"
                            +"<td >"+data[i].isurgent+"</td>"
                            +"<td >"+data[i].iso+"</td>"
                            +"<td >"+"备选"+"</td>"
                            +"<td ><button type='button' class='btn  btn-mini btn-select btn-p4'  onclick=''>选择</button></td>"
                            +"</tr>";
                    }
                    if(len==0){
                        tableStr = tableStr + "<tr style='text-align: center'>"
                            +"<td colspan='6'><font color='#cd0a0a'>"+ '无可选的订单' + "</font></td>"
                            +"</tr>";
                    }
                    tableStr = tableStr + "</table>";
                    //添加到div中
                    $("#listP4").html(tableStr);

                    $("#listP4").delegate("button.btn-p4", "click",
                        function(e) {
                            var myOrderName = "xxx";
                            myOrderName = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                            console.log(myOrderName);
                            var myJson = { orderId : myOrderName} ;

                            $.ajax({
                                type: "post",
                                dataType: "json",
                                data: JSON.stringify(myJson),
                                url: "/OrderManagement/add",
                                contentType: "application/json;charset=utf-8;",
                                success: function (data) {
                                    alert("选单成功");
                                }
                            });

                        });
                }
            })
        }
    });


    $('#tabs a').hover(function(){
        if(!$(this).parent().hasClass("current")){
            $(this).parent().addClass("hoverItem");
        }}, function(){
        $(this).parent().removeClass("hoverItem");
    });




});