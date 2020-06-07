$(document).ready(function () {


    //厂房的操作页面控制代码
    $('#pro-btn').click(function () {

        $('#ok-factory1-buy').show();
        $('#ok-factory1-sale').hide();
        $('#ok-factory1-rent').show();
        $('#ok-factory2-buy').show();
        $('#ok-factory2-sale').hide();
        $('#ok-factory2-rent').show();
        //获取厂房的状态。
        $.ajax({
            type: "post",
            dataType: "json",
            data: {pageSize: 100, pageNum: 0},
            url: "/Factory/list",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {
                data = data['data'];

                console.log(data);
                $.each(data,function(n,value){
                    var mynumber = value.number;
                    var myState = value.state;
                    var productLinesNumber = value.leftCapacity;
                    if(mynumber == "大厂房" )
                    {
                        switch (Number(myState)) {
                            case 1:
                                $('#ok-factory1-buy').hide();
                                if(productLinesNumber>0){ $('#ok-factory1-sale').hide();}
                                else{$('#ok-factory1-sale').show();}
                                $('#ok-factory1-rent').show();
                                $('#factory-1-state').html("自有");
                                break;
                            case 2 :
                                $('#ok-factory1-buy').show();
                                $('#ok-factory1-rent').hide();
                                if(productLinesNumber>0){ $('#ok-factory1-sale').hide();}
                                else{$('#ok-factory1-sale').show();}
                                $('#factory-1-state').html("租赁");
                                break;
                        }

                    }
                    if(mynumber == "小厂房" )
                    {
                        switch (Number(myState)) {
                            case 1:
                                $('#ok-factory2-buy').hide();
                                if(productLinesNumber>0){ $('#ok-factory2-sale').hide();}
                                else{$('#ok-factory2-sale').show();}
                                $('#ok-factory2-rent').show();
                                $('#factory-2-state').html("自有");
                                break;
                            case 2 :
                                $('#ok-factory2-buy').show();
                                $('#ok-factory2-rent').hide();
                                if(productLinesNumber>0){ $('#ok-factory2-sale').hide();}
                                else{$('#ok-factory2-sale').show();}
                                $('#factory-2-state').html("租赁");
                                break;
                        }

                    }
                });


                console.log(data);

            }
        });

        $('.pop-factory').show();
    });


    $('#ok-factory1-buy').click(function () {

        var period=$("#currentAp").val();


        var Factory = {
            period: period,
            number:"大厂房",
            name:"大厂房",
            leftCapacity: 6,
            moneyTotal:40,
            state:1


        };
        //购买厂房。
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(Factory),
            url: "/Factory/add",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {
                data = data['data'];

                alert("成功购买大厂房");
                SetCash();

            }
        });
        $('.pop-factory').hide();
    });


    $('#ok-factory1-sale').click(function () {
        var period=$("#currentAp").val();

        var Factory = {
            number:"大厂房"
        };
        //购买厂房。
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(Factory),
            url: "/Factory/sale",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {

                alert("成功出售大厂房");
                SetCash();

            }
        });
        $('.pop-factory').hide();
    });

    $('#ok-factory1-rent').click(function () {
        var period=$("#currentAp").val();

        var Factory = {
            period:period,
            number:"大厂房"
        };
        //购买厂房。
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(Factory),
            url: "/Factory/rent",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {

                alert("成功租入大厂房");
                SetCash();

            }
        });
        $('.pop-factory').hide();
    });



    $('#ok-factory2-buy').click(function () {

        var period=$("#currentAp").val();
        var Factory = {
            period:period,
            number:"小厂房",
            name:"小厂房",
            leftCapacity: 4,
            moneyTotal:30,
            state:1


        };
        //购买厂房。
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(Factory),
            url: "/Factory/add",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {
                data = data['data'];

                alert("成功购买小厂房");
                SetCash();
            }
        });
        $('.pop-factory').hide();
    });


    $('#ok-factory2-sale').click(function () {
        var period=$("#currentAp").val();

        var Factory = {
            period:period,
            number:"小厂房"
        };
        //购买厂房。
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(Factory),
            url: "/Factory/sale",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {

                alert("成功出售小厂房");
                SetCash();

            }
        });
        $('.pop-factory').hide();
    });


    $('#ok-factory2-rent').click(function () {
        var period=$("#currentAp").val();

        var Factory = {
            period:period,
            number:"小厂房"
        };
        //购买厂房。
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(Factory),
            url: "/Factory/rent",
            contentType: "application/json;charset=utf-8;",
            success: function (data) {

                alert("成功租入小厂房");
                SetCash();

            }
        });
        $('.pop-factory').hide();
    });



    $('.pop-cancel').click(function () {
        $('.pop-factory').hide();
    });

})