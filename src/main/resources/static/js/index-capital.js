/*
*author: Junhao Huang
* day:2020-03-30
*/
$(document).ready(function () {

    //费用显示部分

    $.ajax({
        url: "/AccountingVoucher/listForExpense",
        type: "GET",
        dataType: "json",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            $('#fin-wx').text(parseInt(data['repaires']));
            $('#fin-gg').text(parseInt(data['avdertisedcost']));
            $('#fin-bg').text(parseInt(data['managementcost']));
            $('#fin-zj').text(parseInt(data['depreciation']));
            $('#fin-sds').text(parseInt(data['incometax']));
        }
    });



    //应收账款显示部分
    $.ajax({
        url:"/Salepayment/listReceivable",
        type:"GET",
        dataType:"json",
        contentType: "application/json;charset=utf-8;",
        success: function (data) {
            data = data['data'];
            var len = data.length;
            for(var i=0; i<len;i++){
                var surplusPeriod= data[i].surplusPeriod;
                var money=data[i].money;
                var moneySum1=0;var moneySum2=0;var moneySum3=0;var moneySum4=0;
                switch (surplusPeriod) {
                    case 1:
                        moneySum1=moneySum1+money;
                        break;
                    case 2:
                        moneySum2=moneySum2+money;
                        break;
                    case 3:
                        moneySum3=moneySum3+money;
                        break;
                    case 4:
                        moneySum4=moneySum4+money;
                        break;
                }

            }
            $('#fin-1').attr("value",moneySum1);
            $('#fin-2').attr("value",moneySum2);
            $('#fin-3').attr("value",moneySum3);
            $('#fin-4').attr("value",moneySum4);
        }
    })

    //应收款贴现
    $('#fin-1-ok').click(function() {

        var discountMoney = $('#cash-1').text();
        $.ajax({
            url: "/Salepayment/discountedMoney",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=utf-8;",


        });

        $('#fin-2-ok').click(function () {

            var discountMoney = $('#cash2').text();
        });

        $('#fin-3-ok').click(function () {

            var discountMoney = $('#cash-3').text();
        });

        $('#fin-4-ok').click(function () {

            var discountMoney = $('#cash-4').text();
        })
    })

});