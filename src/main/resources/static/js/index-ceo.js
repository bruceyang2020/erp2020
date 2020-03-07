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
        url: "/Usury/listbyuserandperiod",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
              var gaolidai=0
            data = data['data'];
            // $("#period").text(data[0].period);
            $.each(data, function (n, value) {

                gaolidai=value.moneyTotal+gaolidai;
            });
            $("#ceo-cz-g").val(gaolidai);
            console.log(data);
            console.log('******高利贷数据');
            // $('#year').text(Math.floor(data.period / 4))
            // $('#money_flow').text(data.money_flow)
        }
    });


    //长期贷款的提交按钮事件
    $("#pop-ok").click(function () {
        var LongTermLoans = {
          moneyTotal: $('#load-c').val(),
          period: currentAp
        };
        //可输入负数的bug
        if(parseInt($('#load-c').val())<0){
            alert("请输入正确的长期贷款金额");
            return;
        }
        $.ajax({
            type: "post",
            dataType: "json",
             url: "/LongTermLoans/add",
            contentType: "application/json;charset=utf-8;",
             data: JSON.stringify(LongTermLoans),
             success: function (data) {
            alert("长期贷款成功");
            $('#cash').text(parseInt($('#load-c').val()) + parseInt($('#cash').text()));//财务显示更新
                 $("#ceo-cz-c1").val($('#load-c').val());
            }
        })

    });



    //短期贷款的提交按钮事件
    $("#pop-ok1").click(function () {
        // $('#load-c').value;
        var ShortTermLoan = {
            moneyTotal: $('#load-d').val(),
            period: $('#currentAp').val()
        }
        //可输入负数的bug
        if(parseInt($('#load-d').val())<0){
            alert("请输入正确的短期贷款金额");
            return;
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
                $("#ceo-cz-d1").val($('#load-d').val());
            }
        })

    });

    //高利贷的提交按钮事件
    $("#pop-ok2").click(function () {
        // $('#load-c').value;
        var Usury = {
            moneyTotal: $('#load-g').val(),
            period: $('#currentAp').val()
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/Usury/add",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(Usury),
            success: function (data) {
                alert("高利贷成功");

                $('#cash').text(parseInt($('#load-g').val()) + parseInt($('#cash').text()));//财务显示更新


            }

        })
        $('#ceo-cz-g').val($('#load-g').val());

    });


    /*  投资部分*/

    //市场开拓的显示      marketId=“区域” “国内”  “亚洲”  “国际”
    $.ajax({
        type: "post",
        dataType: "json",
        data: {pageSize: 100, pageNum: 0},
        url: "/MarketFee/list",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            $.each(data, function (n, value) {
                var mymarket = value.marketId;
                var leftperiod = value.periodLeft;
                switch (String(mymarket)) {
                    case "区域":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-ma1").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-ma1").attr("class", "tz1");
                                break;

                        }
                        break;

                    case "国内":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-ma2").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-ma2").attr("class", "tz3");
                                break;
                            case 2:
                                $("#ceo-tz-ma2").attr("class", "tz1");
                                break;

                        }
                        break;

                    case "亚洲":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-ma3").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-ma3").attr("class", "tz3");
                                break;
                            case 2:
                                $("#ceo-tz-ma3").attr("class", "tz2");
                                break;
                            case 3:
                                $("#ceo-tz-ma3").attr("class", "tz1");
                                break;

                        }
                        break;

                    case "国际":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-ma4").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-ma4").attr("class", "tz4");
                                break;
                            case 2:
                                $("#ceo-tz-ma4").attr("class", "tz3");
                                break;
                            case 3:
                                $("#ceo-tz-ma4").attr("class", "tz2");
                                break;
                            case 4:
                                $("#ceo-tz-ma4").attr("class", "tz1");
                                break;

                        }
                        break;
                }

            });

        }
    });
    //产品研发的显示
    $.ajax({
        type: "post",
        dataType: "json",
        data: {pageSize: 100, pageNum: 0},
        url: "/ResearchFee/list",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            $.each(data, function (n, value) {
                var myproduct = value.productId;
                var leftperiod = value.periodLeft;
                switch (String(myproduct)) {
                    //出于代码的完整性，还是加上了P1
                    case "P1":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-pr1").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-pr1").attr("class", "tz4");
                                break;
                            case 2:
                                $("#ceo-tz-pr1").attr("class", "tz3_5");
                                break;
                            case 3:
                                $("#ceo-tz-pr1").attr("class", "tz3");
                                break;
                            case 4:
                                $("#ceo-tz-pr1").attr("class", "tz2");
                                break;
                            case 5:
                                $("#ceo-tz-pr1").attr("class", "tz1_5");
                                break;
                            case 6:
                                $("#ceo-tz-pr1").attr("class", "tz1");
                                break;
                        }
                        break;
                    case "P2":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-pr2").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-pr2").attr("class", "tz4");
                                break;
                            case 2:
                                $("#ceo-tz-pr2").attr("class", "tz3_5");
                                break;
                            case 3:
                                $("#ceo-tz-pr2").attr("class", "tz3");
                                break;
                            case 4:
                                $("#ceo-tz-pr2").attr("class", "tz2");
                                break;
                            case 5:
                                $("#ceo-tz-pr2").attr("class", "tz1_5");
                                break;
                            case 6:
                                $("#ceo-tz-pr2").attr("class", "tz1");
                                break;
                        }
                        break;
                    case "P3":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-pr3").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-pr3").attr("class", "tz4");
                                break;
                            case 2:
                                $("#ceo-tz-pr3").attr("class", "tz3_5");
                                break;
                            case 3:
                                $("#ceo-tz-pr3").attr("class", "tz3");
                                break;
                            case 4:
                                $("#ceo-tz-pr3").attr("class", "tz2");
                                break;
                            case 5:
                                $("#ceo-tz-pr3").attr("class", "tz1_5");
                                break;
                            case 6:
                                $("#ceo-tz-pr3").attr("class", "tz1");
                                break;
                        }
                        break;
                    case "P4":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-pr4").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-pr4").attr("class", "tz4");
                                break;
                            case 2:
                                $("#ceo-tz-pr4").attr("class", "tz3_5");
                                break;
                            case 3:
                                $("#ceo-tz-pr4").attr("class", "tz3");
                                break;
                            case 4:
                                $("#ceo-tz-pr4").attr("class", "tz2");
                                break;
                            case 5:
                                $("#ceo-tz-pr4").attr("class", "tz1_5");
                                break;
                            case 6:
                                $("#ceo-tz-pr4").attr("class", "tz1");
                                break;
                        }
                        break;


                }


            });
        }
    });

    //ISO认证显示
    $.ajax({
        type: "post",
        dataType: "json",
        data: {pageSize: 100, pageNum: 0},
        url: "/IsoFee/list",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            $.each(data, function (n, value) {
                var mynumber = value.number;
                var leftperiod = value.periodLeft;
                switch (String(mynumber)) {
                    case "ISO9K":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-iso1").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-iso1").attr("class", "tz3");
                                break;
                            case 2:
                                $("#ceo-tz-iso1").attr("class", "tz1");
                                break;

                        }
                        break;

                    case "ISO14K":
                        switch (Number(leftperiod)) {
                            case 0:
                                $("#ceo-tz-iso2").attr("class", "tz5");
                                break;
                            case 1:
                                $("#ceo-tz-iso2").attr("class", "tz3");
                                break;
                            case 2:
                                $("#ceo-tz-iso2").attr("class", "tz1");
                                break;

                        }
                        break;

                }

            });
        }
    });


    //区域投资按钮确定事件
    $("#ok-qy").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "区域"
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
        // touzi: 0
        $("#cash").text(parseInt($('#cash').text()) - parseInt("20"));//区域扣20
        touzi = parseInt(touzi) + parseInt("20");//记录扣款数
        //touzi: 20

    });
    //区域投资按钮取消事件
    $("#cancel-qy").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "区域"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("区域市场开拓取消成功");
            }
        });


        $("#cash").text(parseInt($('#cash').text()) + parseInt("20"));//区域加20
        $('.pop-s4').hide();
        var battery = ["tz1", "tz5"];
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-ma1').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                } else {
                    $('#ceo-tz-ma1').attr("class", battery[i - 1]);
                    break;
                }
            }

        }
    });

    //国内投资按钮确定事件
    $("#ok-gn").click(function () {

        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "国内"
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
        });
        $("#cash").text(parseInt($('#cash').text()) - parseInt("40"));//国内扣40
        touzi = parseInt(touzi) + parseInt("40");//记录扣款数
    });
    //国内投资按钮取消事件
    $("#cancel-gn").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "国内"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("国内市场开拓取消成功");
            }
        });

        $("#cash").text(parseInt($('#cash').text()) + parseInt("40"));//国内加40

        var battery = ["tz1", "tz3", "tz5"];
        $('.pop-s5').hide();
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-ma2').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                } else {
                    $('#ceo-tz-ma2').attr("class", battery[i - 1]);
                    break;
                }
            }
        }

    });


    //亚洲投资按钮确定事件
    $("#ok-yz").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "亚洲"
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
    });
    //亚洲投资按钮取消事件
    $("#cancel-yz").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "亚洲"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("亚洲市场开拓取消成功");
            }
        });

        $("#cash").text(parseInt($('#cash').text()) + parseInt("60"));//亚洲加60

        var battery = ["tz1", "tz2", "tz3", "tz5"];
        $('.pop-s6').hide();
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-ma3').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                } else {
                    $('#ceo-tz-ma3').attr("class", battery[i - 1]);
                    break;
                }
            }
        }

    });

    //国际投资按钮确定事件
    $("#ok-gj").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "国际"
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
    //国际投资按钮取消事件
    $("#cancel-gj").click(function () {
        var MarketFee = {
            period: $('#currentAp').val(),
            marketId: "国际"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/MarketFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(MarketFee),
            success: function (data) {
                alert("国际市场开拓取消成功");
            }
        });

        $("#cash").text(parseInt($('#cash').text()) + parseInt("80"));//国际加80

        var battery = ["tz1", "tz2", "tz3", "tz4", "tz5"];
        $('.pop-s7').hide();
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-ma4').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                } else {
                    $('#ceo-tz-ma4').attr("class", battery[i - 1]);
                    break;
                }
            }
        }

    });

    //产品研发投资-P2
    $("#ok-p2").click(function () {
        var ResearchFee = {
            period: $('#currentAp').val(),
            productId: "P2"
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
        });
        $("#cash").text(parseInt($('#cash').text()) - parseInt("20"));//P2扣20
        touzi = parseInt(touzi) + parseInt("20");//记录扣款数
    });
    //产品研发取消按钮-p2
    $("#cancel-p2").click(function () {
        var ResearchFee = {
            period: $('#currentAp').val(),
            productId: "P2"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ResearchFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("P2产品研发取消成功");
            }
        });

        $("#cash").text(parseInt($('#cash').text()) + parseInt("20"));//P2加20

        var battery = ["tz1","tz1_5","tz2","tz3","tz3_5","tz4","tz5"];
        $('.pop-s9').hide();
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-pr2').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                }
                else {
                    $('#ceo-tz-pr2').attr("class", battery[i-1]);
                    break;
                }
            }
        }

    });


        //产品研发投资-P3
    $("#ok-p3").click(function () {
        var ResearchFee = {
                period: $('#currentAp').val(),
                productId: "P3"
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
    //产品研发取消按钮-p3
    $("#cancel-p3").click(function () {
        var ResearchFee = {
            period: $('#currentAp').val(),
            productId: "P3"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ResearchFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("P3产品研发取消成功");
            }
        });

        $("#cash").text(parseInt($('#cash').text()) + parseInt("20"));//P3加20

        var battery = ["tz1","tz1_5","tz2","tz3","tz3_5","tz4","tz5"];
        $('.pop-s10').hide();
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-pr3').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                }
                else {
                    $('#ceo-tz-pr3').attr("class", battery[i-1]);
                    break;
                }
            }
        }

    });
        //产品研发投资-P4
        $("#ok-p4").click(function () {
            var ResearchFee = {
                period: $('#currentAp').val(),
                productId: "P4"
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

    //产品研发取消按钮-p4
    $("#cancel-p4").click(function () {
        var ResearchFee = {
            period: $('#currentAp').val(),
            productId: "P4"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ResearchFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ResearchFee),
            success: function (data) {
                alert("P4产品研发取消成功");
            }
        });

        $("#cash").text(parseInt($('#cash').text()) + parseInt("20"));//P4加20

        var battery = ["tz1","tz1_5","tz2","tz3","tz3_5","tz4","tz5"];
        $('.pop-s11').hide();
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-pr4').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                }
                else {
                    $('#ceo-tz-pr4').attr("class", battery[i-1]);
                    break;
                }
            }
        }

    });


        //ISO认证投资-ISO9000
        $("#ok-9k").click(function () {
            var IsoFee = {
                period: $('#currentAp').val(),
                number: "ISO9K"
            }
            $.ajax({
                type: "post",
                dataType: "json",
                url: "/IsoFee/add",
                contentType: "application/json;charset=utf-8;",
                data: JSON.stringify(IsoFee),
                success: function (data) {
                    alert("ISO9K研发成功");
                    console.log(data);
                    console.log("*************ISO9K");
                }
            });
            $("#cash").text(parseInt($('#cash').text()) - parseInt("200"));//ISO1扣200
            touzi = parseInt(touzi) + parseInt("200");//记录扣款数
        });
    //ISO9K投资按钮取消事件
    $("#cancel-9k").click(function () {
        var IsoFee = {
            period: $('#currentAp').val(),
            number: "ISO9K"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/IsoFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(IsoFee),
            success: function (data) {
                alert("ISO9K认证取消成功");
            }
        });


        $("#cash").text(parseInt($('#cash').text()) + parseInt("200"));//ISO9K加200
        $('.pop-s12').hide();
        var battery = ["tz1","tz3","tz5"];
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-iso1').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                } else {
                    $('#ceo-tz-iso1').attr("class", battery[i - 1]);
                    break;
                }
            }
        }
    });

        //ISO认证投资-ISO14000
    $("#ok-14k").click(function () {
            var IsoFee = {
                period: $('#currentAp').val(),
                number: "ISO14K"
            }
            $.ajax({
                type: "post",
                dataType: "json",
                url: "/IsoFee/add",
                contentType: "application/json;charset=utf-8;",
                data: JSON.stringify(IsoFee),
                success: function (data) {
                    alert("ISO14K研发成功");
                    console.log(data);
                    console.log("**********************ISO14K")
                }
            });
            $("#cash").text(parseInt($('#cash').text()) - parseInt("400"));//ISO2扣400
            touzi = parseInt(touzi) + parseInt("400");//记录扣款数
        });
    //ISO14K投资按钮取消事件
    $("#cancel-14k").click(function () {
        var IsoFee = {
            period: $('#currentAp').val(),
            number: "ISO14K"
        }
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/IsoFee/deleteByPeriod",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(IsoFee),
            success: function (data) {
                alert("ISO14K认证取消成功");
            }
        });


        $("#cash").text(parseInt($('#cash').text()) + parseInt("400"));//ISO14K加400
        $('.pop-s13').hide();
        var battery = ["tz1","tz3","tz5"];
        for (var i = 0; i < battery.length; i++) {

            if ($('#ceo-tz-iso2').hasClass(battery[i])) {
                if (i == 0) {
                    break;
                }
                else {
                    $('#ceo-tz-iso2').attr("class", battery[i-1]);
                    break;
                }
            }

        }
    });
})
