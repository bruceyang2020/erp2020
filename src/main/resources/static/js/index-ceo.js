$(document).ready(function () {

    var currentAp = $("#currentAp").val();
    var currentTeam = $("#currentTeam").val();


    var daikuan = 0;//贷款数
    var touzi = 0;//投资额
    var dindan = 0;//订单数

    //贷款显示-长期贷款
    $.ajax({
        type: "post",
        dataType: "json",
        data: {pageSize: 100, pageNum: 0},
        url: "/LongTermLoans/listbyuserandperiod",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            $.each(data, function (n, value) {
                var i = 6 - ((value.returnTime - 1) / 4);
                console.log(i);
                $("#ceo-cz-c" + i).val(value.moneyTotal);
            });

            console.log(data);
            console.log('*****长期贷款数据');
        }
    });


    //短期贷款的显示
    $.ajax({
        type: "post",
        dataType: "json",
        data: {pageSize: 100, pageNum: 0},
        url: "/ShortTermLoan/listbyuserandperiod",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            $.each(data, function (n, value) {
                var currentAp = $("#currentAp").val();
                var i = 1 + currentAp * 1 - value.period;
                console.log(i);
                $("#ceo-cz-d" + i).val(value.moneyTotal);
            });


            console.log(data);
            console.log('*******短期贷款数据');
        }
    });


    //高利贷的显示
    $.ajax({
        type: "post",
        dataType: "json",
        data: {pageSize: 100, pageNum: 0},
        url: "/Usury/list",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            // $("#period").text(data[0].period);
            $("#ceo-cz-g").val(data[0].moneyTotal);
            console.log(data);
            console.log('******高利贷数据');
            // $('#year').text(Math.floor(data.period / 4))
            // $('#money_flow').text(data.money_flow)
        }
    });



    //长期贷款的提交按钮事件
    $("#pop-ok").click(function () {


        var LongTermLoans = {
            moneyTotal:$('#load-c').val(),
            period: $('#currentAp').val()

        };
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/LongTermLoans/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(LongTermLoans),
            success: function (data) {
                alert("长期贷款成功");
                $('#cash').text(parseInt($('#load-c').val()) + parseInt($('#cash').text()));//财务显示更新
            }
        })
        $("#ceo-cz-c1").val($('#load-c').val());
    });

    //短期贷款的提交按钮事件
    $("#pop-ok1").click(function () {
        // $('#load-c').value;
        var ShortTermLoan = {
            moneyTotal:$('#load-d').val(),
            period: $('#currentAp').val()
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ShortTermLoan/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ShortTermLoan),
            success: function (data) {
                alert("短期贷款成功");
                daikuan = parseInt(daikuan) + parseInt($('#load-d').val());//记录贷款数
                $('#cash').text(parseInt($('#load-d').val()) + parseInt($('#cash').text()));//财务显示更新
            }
        })
        $("#ceo-cz-d1").val($('#load-d').val());
    });

    //高利贷的提交按钮事件
    $("#pop-ok2").click(function () {
        // $('#load-c').value;
        var Usury = {
            id: "1",
            longTermLoan: $('#load-g').val(),
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/Usury/update",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(Balancesheet),
            success: function (data) {
                alert("高利贷成功");
            }
        })

    });



  /*  投资部分*/
    //区域投资
    $("#ok-qy").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId:"区域"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("区域市场开拓成功");
            }
        })

        $("#cash").text(parseInt($('#cash').text()) - parseInt("20"));//区域扣20
        touzi = parseInt(touzi) + parseInt("20");//记录扣款数
    });

    //国内投资
    $("#ok-gn").click(function () {

        var MarketFee = {
            period: $('#currentAp').val(),
            marketId:"国内"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("国内市场开拓成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("40"));//国内扣40
        touzi = parseInt(touzi) + parseInt("40");//记录扣款数
    })

    //亚洲投资
    $("#ok-yz").click(function () {

        var MarketFee = {
            period: $('#currentAp').val(),
            marketId:"亚洲"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("亚洲市场开拓成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("60"));//亚洲扣60
        touzi = parseInt(touzi) + parseInt("60");//记录扣款数
    })

    //国际投资
    $("#ok-gj").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId:"国际"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("国际市场开拓成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("80"));//国际扣80
        touzi = parseInt(touzi) + parseInt("80");//记录扣款数
    })

    //产品研发投资-P2
    $("#ok-p2").click(function () {
        var ResearchFee = {
            period: $('#currentAp').val(),
            productId:"P2"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ResearchFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("P2研发成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("20"));//P2扣20
        touzi = parseInt(touzi) + parseInt("20");//记录扣款数
    })

    //产品研发投资-P3
    $("#ok-p3").click(function () {
        var ResearchFee = {
            period: $('#currentAp').val(),
            productId:"P3"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ResearchFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("P3研发成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("20"));//P3扣20
        touzi = parseInt(touzi) + parseInt("20");//记录扣款数
    })

    //产品研发投资-P4
    $("#ok-p4").click(function () {
        var ResearchFee = {
            period: $('#currentAp').val(),
            productId:"P4"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ResearchFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("P4研发成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("20"));//P4扣20
        touzi = parseInt(touzi) + parseInt("20");//记录扣款数
    })


    //ISO认证投资-ISO9000
    $("#ok-9k").click(function () {
        var IsoFee = {
            period: $('#currentAp').val(),
            number:"ISO9K"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/IsoFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("ISO9K研发成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("200"));//ISO1扣200
        touzi = parseInt(touzi) + parseInt("200");//记录扣款数
    })


    //ISO认证投资-ISO14000
    $("#ok-14k").click(function () {
        var IsoFee = {
            period: $('#currentAp').val(),
            number:"ISO14K"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/IsoFee/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("ISO14K研发成功");
            }
        })
        $("#cash").text(parseInt($('#cash').text()) - parseInt("400"));//ISO2扣400
        touzi = parseInt(touzi) + parseInt("400");//记录扣款数
    })


})
