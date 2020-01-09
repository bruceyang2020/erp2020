var arr = [1,5,9,13,17,21]
var money_now = 180
Mock.mock('http://api.com', {
    'period|1': arr,
    "money_now|10-99": 1,
    "money_get|10-99": 1,
    "money_making|10-99": 1,
    "money_sale|10-99": 1,
    "money_buy|10-99": 1,
    "money_flow|10-99": 1,
    "long_term_loan|10-99": 1,
    "short_term_loan|10-99": 1,
    "money_order_get|10-99": 1,
    "money_tax|10-99": 1,
    "long_term_loan_year|10-99": 1,
    "money_loan|10-99": 1,
    "money_j|10-99": 1,
    "money_p|10-99": 1,
    "money_m|10-99": 1,
    "money_static|10-99": 1,
    "money_g|10-99": 1,
    "money_stay|10-99": 1,
    "money_year|10-99": 1,
    "money_user|10-99": 1,
    "money_all|10-99": 1,
    "money_alls|10-99": 1,

    "income_sale|70-80":1,
    "income_l|60-70":1,
    "money_new|50-60":1,
    "money_norate|40-50":1,
    "money_notax|30-40":1,
    "money_income|20-30":1,

    "money_total_1|50-100":1,
    "money_total_2|50-100":1,
    "money_total_3|50-100":1,
    "money_total_4|50-100":1,
    "money_total_5|50-100":1,
    "money_total_6|50-100":1,

    "money_total|50-100":1,

    //"cash|150-150":1,
    "money1|50-100":1,
    "money2|50-100":1,
    "money3|50-100":1,
    "money4|50-100":1,
    "fin_wx|20-40":1,
    "fin_gg|10-50":1,
    "fin_bg|20-50":1,
    "fin_zj|40-50":1,
    "fin_tax|50-60":1,

    "p1|0-20":1,
    "p2|0-30":1,
    "p3|0-10":1,
    "p4|0-20":1,
    "r1|0-30":1,
    "r2|0-20":1,
    "r3|0-20":1,
    "r4|0-20":1,
    "r1_1|0-20":1,
    "r2_1|0-20":1,
    "r3_1|0-20":1,
    "r3_2|0-20":1,
    "r4_1|0-20":1,
    "r4_2|0-20":1,

    "order|12":[{
        "number|0-10":1,
        "price_total|10-30":1,
        "amount|0-10":1,
        "period_pay|1-6":1,
        "period|1-6":1,
        "iso|0-1":1,

    }],
    "product_line|12":[{
        "product_line_number|1-10":1, //生产线编号
        "state|0-1":1,//生产线状态
        "processing_cycle|1-4":1,
        "number|1-2":1,//厂房类型，大厂房1，小厂房2
    }],

    "productline_type_id|1-4":1
});