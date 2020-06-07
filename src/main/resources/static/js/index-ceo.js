$(document).ready(function () {

    var currentAp = $("#currentAp").val();
    var currentTeam = $("#currentTeam").val();

    var stateqy=0;var stategn=0;var stateyz=0;var stategj=0;//H 市场开拓是否完成
    var statep1=0;var statep2=0;var statep3=0;var statep4=0;//H 产品研发是否完成
    var stateiso1=0;var stateiso2=0;//H ISO是否完成

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
            period: $('#currentAp').val()
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
                 $("#ceo-cz-c1").val($('#load-c').val());
                 SetCash();
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
                $("#ceo-cz-d1").val($('#load-d').val());
                SetCash();
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
                if($('#load-g').val()>=0){alert("高利贷借款成功")}
                else{alert("高利贷还款成功")};
                SetCash();

            }

        })
        $('#ceo-cz-g').val($('#load-g').val());

    });


    /*  投资部分*/
    /*小电池模块tz1 tz1_5 tz2 tz2_5 tz3 tz3_5 tz4 tz5 可用于改动规则*/

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
                        var battery = ["tz5","tz1"];    //H 可通过添加随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-ma1').attr("class", battery[leftperiod]);//H 0是完成
                        stateqy=value.state;
                        break;

                    case "国内":
                        var battery = ["tz5","tz3","tz1"];    //H 可通过添加随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-ma2').attr("class", battery[leftperiod]);//H 0是完成
                        stategn=value.state;
                        break;

                    case "亚洲":
                        var battery = ["tz5","tz3","tz2","tz1"];    //H 可通过添加随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-ma3').attr("class", battery[leftperiod]);//H 0是完成
                        stateyz=value.state;
                        break;

                    case "国际":
                        var battery = ["tz5","tz4","tz3","tz2","tz1"];    //H 可通过添加随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-ma4').attr("class", battery[leftperiod]);//H 0是完成
                        stategj=value.state;
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
                    case "P1":
                        var battery = ["tz5","tz1"];    //H 可通过添加删改随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-pr1').attr("class", battery[leftperiod]);//H 0是完成
                        statep1=value.state;
                        break;

                    case "P2":
                        var battery = ["tz5","tz4","tz3_5","tz3","tz2","tz1_5","tz1"];    //H 可通过添加随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-pr2').attr("class", battery[leftperiod]);//H 0是完成
                        statep2=value.state;
                        break;

                    case "P3":
                        var battery =["tz5","tz4","tz3_5","tz3","tz2","tz1_5","tz1"];     //H 可通过添加随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-pr3').attr("class", battery[leftperiod]);//H 0是完成
                        statep3=value.state;
                        break;

                    case "P4":
                        var battery =["tz5","tz4","tz3_5","tz3","tz2","tz1_5","tz1"];     //H 可通过添加随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-pr4').attr("class", battery[leftperiod]);//H 0是完成
                        statep4=value.state;
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
                        var battery = ["tz5","tz3","tz1"];    //H 可通过添加删改随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-iso1').attr("class", battery[leftperiod]);//H 0是完成
                        stateiso1=value.state;
                        break;
                    case "ISO14K":
                        var battery = ["tz5","tz3","tz1"];    //H 可通过添加删改随意更改规则, leftPeriod 0 为1 倒序
                        $('#ceo-tz-iso2').attr("class", battery[leftperiod]);//H 0是完成
                        stateiso2=value.state;
                        break;
                }
            });
        }
    });

    // H 投资按钮
    //H 市场开拓
    var qy=0;var gn=0;var yz=0;var gj=0;//H 判断当期投了几次
    //区域投资按钮确定事件
    $("#ok-qy").click(function () {
        if(qy==0){
            qy=1;
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
                SetCash();
            }
        })

        //H 前端battery显示变化
        var battery = ["tz1", "tz5"];    //H 可通过添加随意更改
        for (var i = 0; i < battery.length; i++) {
            if ($('#ceo-tz-ma1').hasClass(battery[i])) {
                if (i == 1) {
                    break;
                }
                else {
                    $('#ceo-tz-ma1').attr("class", battery[i+1]);
                    break;
                }
            }
        }}
        else{alert("您已投资");}
    });

    //区域投资按钮取消事件
    $("#cancel-qy").click(function () {
        if(qy==1) {
            if(stateqy==0) {
                qy = 0;
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
                        SetCash();
                    }
                });
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
            }
        }else {alert("请投资!")}
    });

    //国内投资按钮确定事件
    $("#ok-gn").click(function () {
        if(gn==0) {
                gn = 1;
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
                        SetCash();
                    }
                });
                touzi = parseInt(touzi) + parseInt("40");//记录扣款数
                //H 前端battery显示变化
                var battery = ["tz1", "tz3", "tz5"];    //H 可通过添加随意更改
                for (var i = 0; i < battery.length; i++) {
                    if ($('#ceo-tz-ma2').hasClass(battery[i])) {
                        if (i == 2) {
                            break;
                        } else {
                            $('#ceo-tz-ma2').attr("class", battery[i + 1]);
                            break;
                        }
                    }
                }
        }
        else{alert("您已投资");}
    });
    //国内投资按钮取消事件
    $("#cancel-gn").click(function () {
        if(gn==1) {
            if(stategn==0) {
                gn = 0;
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
                        SetCash();
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
            }
        }
        else{alert("请投资")}
    });

    //亚洲投资按钮确定事件
    $("#ok-yz").click(function () {
        if(yz==0) {
            yz = 1;

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
                    SetCash();
                }
            })
            touzi = parseInt(touzi) + parseInt("60");//记录扣款数
            //H 前端battery显示变化
            var battery = ["tz1", "tz2", "tz3", "tz5"];    //H 可通过添加随意更改
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-ma3').hasClass(battery[i])) {
                    if (i == 3) {
                        break;
                    } else {
                        $('#ceo-tz-ma3').attr("class", battery[i + 1]);
                        break;
                    }
                }
            }
        }else{alert("您已投资")}
    });
    //亚洲投资按钮取消事件
    $("#cancel-yz").click(function () {
        if(yz==1) {
            if(stateyz==0) {
                yz = 0;
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
                        SetCash();
                    }
                });


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
            }
        }
        else{alert("请投资")}

    });

    //国际投资按钮确定事件
    $("#ok-gj").click(function () {
        if(gj==0) {
            gj = 1;
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
                    SetCash();
                }
            })
            touzi = parseInt(touzi) + parseInt("80");//记录扣款数

            //H 前端battery显示变化
            var battery = ["tz1", "tz2", "tz3", "tz4", "tz5"];    //H 可通过添加随意更改
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-ma4').hasClass(battery[i])) {
                    if (i == 4) {
                        break;
                    } else {
                        $('#ceo-tz-ma4').attr("class", battery[i + 1]);
                        break;
                    }
                }
            }
        }
        else{alert("您已投资")}
    })
    //国际投资按钮取消事件
    $("#cancel-gj").click(function () {
        if(gj==1) {
            if (stategj == 0) {
                gj = 0;
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
                        SetCash();
                    }
                });


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
            }
        }
        else{alert("请投资")}

    });
    var cp1=0;var cp2=0;var cp3=0;var cp4=0;//H 判断当期投了几次

    //产品研发投资-P1
    $("#ok-p1").click(function () {
        if(cp1==0) {
            cp1 = 1;
            var ResearchFee = {
                period: $('#currentAp').val(),
                productId: "P1"
            }
            $.ajax({
                type: "post",
                dataType: "json",
                url: "/ResearchFee/add",
                contentType: "application/json;charset=utf-8;",
                data: JSON.stringify(ResearchFee),
                success: function (data) {
                    alert("P1研发成功");
                    SetCash();
                }
            });
            //H 前端battery显示变化
            var battery = ["tz1", "tz5"];    //H 可通过添加随意更改
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-pr1').hasClass(battery[i])) {
                    if (i == 1) {
                        break;
                    } else {
                        $('#ceo-tz-pr1').attr("class", battery[i + 1]);
                        break;
                    }
                }
            }

            touzi = parseInt(touzi) + parseInt("20");//记录扣款数
        }
        else{alert("您已投资");}
    });
    //产品研发取消按钮-p1
    $("#cancel-p1").click(function () {
        if(cp1==1) {
            if(statep1==0) {
                cp1 = 0;
                var ResearchFee = {
                    period: $('#currentAp').val(),
                    productId: "P1"
                }
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "/ResearchFee/deleteByPeriod",
                    contentType: "application/json;charset=utf-8;",
                    data: JSON.stringify(ResearchFee),
                    success: function (data) {
                        alert("P1产品研发取消成功");
                        SetCash();
                    }
                });
                //H 前端battery显示变化
                var battery = ["tz1", "tz5"];    //H 可通过添加随意更改
                $('.pop-s9').hide();
                for (var i = 0; i < battery.length; i++) {
                    if ($('#ceo-tz-pr1').hasClass(battery[i])) {
                        if (i == 0) {
                            break;
                        } else {
                            $('#ceo-tz-pr1').attr("class", battery[i - 1]);
                            break;
                        }
                    }
                }
            }
        }
        else{alert("请投资");}

    });

    //产品研发投资-P2
    $("#ok-p2").click(function () {
        if(cp2==0) {
            cp2 = 1;
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
                    SetCash();
                }
            });
            touzi = parseInt(touzi) + parseInt("20");//记录扣款数

            //H 前端battery显示变化
            var battery = ["tz1", "tz1_5", "tz2", "tz3", "tz3_5", "tz4", "tz5"];
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-pr2').hasClass(battery[i])) {
                    if (i == 6) {
                        break;
                    } else {
                        $('#ceo-tz-pr2').attr("class", battery[i + 1]);
                        break;
                    }
                }
            }
        }
        else{alert("您已投资");}
    });
    //产品研发取消按钮-p2
    $("#cancel-p2").click(function () {
        if(cp2==1) {
            if (statep2 == 0) {
                cp2 = 0;
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
                        SetCash();
                    }
                });


                //H 前端battery显示变化
                var battery = ["tz1", "tz1_5", "tz2", "tz3", "tz3_5", "tz4", "tz5"];
                for (var i = 0; i < battery.length; i++) {
                    if ($('#ceo-tz-pr2').hasClass(battery[i])) {
                        if (i == 0) {
                            break;
                        } else {
                            $('#ceo-tz-pr2').attr("class", battery[i - 1]);
                            break;
                        }
                    }
                }
            }
        }
        else{alert("请投资");}
    });


        //产品研发投资-P3
    $("#ok-p3").click(function () {
        if(cp3==0) {
            cp3 = 1;
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
                    SetCash();
                }
            })
            touzi = parseInt(touzi) + parseInt("20");//记录扣款数
            //H 前端battery显示变化
            var battery = ["tz1", "tz1_5", "tz2", "tz3", "tz3_5", "tz4", "tz5"];
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-pr3').hasClass(battery[i])) {
                    if (i == 6) {
                        break;
                    } else {
                        $('#ceo-tz-pr3').attr("class", battery[i + 1]);
                        break;
                    }
                }
            }
            touzi = parseInt(touzi) + parseInt("20");//记录扣款数
        }else{alert("您已投资");}
        })
    //产品研发取消按钮-p3
    $("#cancel-p3").click(function () {
        if(cp3==1) {
            if(statep3==0) {
                cp3 = 0;

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
                        SetCash();
                    }
                });
                //H 前端battery显示变化
                var battery = ["tz1", "tz1_5", "tz2", "tz3", "tz3_5", "tz4", "tz5"];
                for (var i = 0; i < battery.length; i++) {
                    if ($('#ceo-tz-pr3').hasClass(battery[i])) {
                        if (i == 0) {
                            break;
                        } else {
                            $('#ceo-tz-pr3').attr("class", battery[i - 1]);
                            break;
                        }
                    }
                }
            }
        }
        else{alert("请投资");}

    });
        //产品研发投资-P4
        $("#ok-p4").click(function () {
            if(cp4==0) {
                cp4 = 1;
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
                        SetCash();
                    }
                })

                touzi = parseInt(touzi) + parseInt("20");//记录扣款数
                //H 前端battery显示变化
                var battery = ["tz1", "tz1_5", "tz2", "tz3", "tz3_5", "tz4", "tz5"];
                for (var i = 0; i < battery.length; i++) {
                    if ($('#ceo-tz-pr4').hasClass(battery[i])) {
                        if (i == 6) {
                            break;
                        } else {
                            $('#ceo-tz-pr4').attr("class", battery[i + 1]);
                            break;
                        }
                    }
                }
            }else{alert("您已投资")}
            })

    //产品研发取消按钮-p4
    $("#cancel-p4").click(function () {
        if(cp4==1) {
            if(statep4==0) {
                cp4 = 0;
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
                        SetCash();
                    }
                });
                //H 前端battery显示变化
                var battery = ["tz1", "tz1_5", "tz2", "tz3", "tz3_5", "tz4", "tz5"];
                $('.pop-s11').hide();
                for (var i = 0; i < battery.length; i++) {
                    if ($('#ceo-tz-pr4').hasClass(battery[i])) {
                        if (i == 0) {
                            break;
                        } else {
                            $('#ceo-tz-pr4').attr("class", battery[i - 1]);
                            break;
                        }
                    }
                }
            }
        }
        else {alert("请投资")};

    });

    var Iso1=0;var Iso2=0;//H 判断当期投了几次
        //ISO认证投资-ISO9000
        $("#ok-9k").click(function () {
            if(Iso1==0){
                Iso1=1;//在这一期间
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
                    SetCash();
                }
            });

            touzi = parseInt(touzi) + parseInt("200");//记录扣款数
            //H 前端battery显示变化
            var battery = ["tz1","tz3","tz5"];
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-iso1').hasClass(battery[i])) {
                    if (i == 2) {
                        break;
                    } else {
                        $('#ceo-tz-iso1').attr("class", battery[i + 1]);
                        break;
                    }
                }
            }
            }
            else{alert("您已投资！");}
            });
    //ISO9K投资按钮取消事件
    $("#cancel-9k").click(function () {
        if(Iso1==1){
            if(stateiso1==0){
            Iso1=0;
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
                SetCash();
            }

        });

        //H 前端battery显示变化
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
        }}}
        else{
            alert("请投资");
            }
    });

        //H ISO认证投资-ISO14000
    $("#ok-14k").click(function () {
        if(Iso2==0) {
            Iso2 = 1;
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
                    console.log("**********************ISO14K");
                    SetCash();
                }

            });
            //H 前端battery显示变化
            var battery = ["tz1", "tz3", "tz5"];
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-iso2').hasClass(battery[i])) {
                    if (i == 2) {
                        break;
                    } else {
                        $('#ceo-tz-iso2').attr("class", battery[i + 1]);
                        break;
                    }
                }

            }

            touzi = parseInt(touzi) + parseInt("400");//记录扣款数
        }else{alert("您已投资")}
        });
    //H ISO14K投资按钮取消事件
    $("#cancel-14k").click(function () {
        if(Iso2 ==1) {
            if(stateiso2==0){
            Iso2=0;
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
                    SetCash();
                }
            });
            //H 前端battery显示变化
            var battery = ["tz1", "tz3", "tz5"];
            for (var i = 0; i < battery.length; i++) {
                if ($('#ceo-tz-iso2').hasClass(battery[i])) {
                    if (i == 0) {
                        break;
                    } else {
                        $('#ceo-tz-iso2').attr("class", battery[i - 1]);
                        break;
                    }
                }

            }}
        }else {alert("请投资");}
    });
})
