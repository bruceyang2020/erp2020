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
            $('#fin-sds').text(parseInt(data['incometax'])>0?parseInt(data['incometax'])>0:0);//H 所得税负数不显示
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
            var moneySum1=0;var moneySum2=0;var moneySum3=0;var moneySum4=0;

            for(var i=0; i<len;i++){

                var surplusPeriod= data[i].surplusPeriod;
                var money=data[i].money;
                var amount=data[i].amount;
                switch (surplusPeriod) {
                    case 1:
                        moneySum1=moneySum1+money-amount;
                        console.log(moneySum1);
                        break;
                    case 2:
                        moneySum2=moneySum2+money-amount;
                        console.log(moneySum2);
                        break;
                    case 3:
                        moneySum3=moneySum3+money-amount;
                        break;
                    case 4:
                        moneySum4=moneySum4+money-amount;
                        break;
                }

            }
            $('#fin-1').attr("value",moneySum1);
            $('#fin-2').attr("value",moneySum2);
            $('#fin-3').attr("value",moneySum3);
            $('#fin-4').attr("value",moneySum4);
        }
    })

    //H 应收款贴现
    //20200727 H 还缺少一个显示刷新
    $('#fin-ok').click(function() {

        var discountedAmount ={
            amount: $('#cash-1').val()
        };


        $.ajax({
            url: "/Salepayment/discountedMoney",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(discountedAmount),
            success: function (data) {
                var myMsg =data['msg'];
                if(myMsg=="OK"){
                    SetCash();
                    alert("贴现成功");
                }
                else{
                    alert("请输入正确的金额");
                }

            }
        });
    })
});