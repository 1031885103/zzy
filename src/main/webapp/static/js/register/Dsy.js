//赋值省级下拉框
$(function () {
    for (var i = 0; i < result.data[0].length; i++) {
        $("<option></option>")
            .val(result.data[0][i].province)
            .text(result.data[0][i].province)
            .attr("val", result.data[0][i].provinceid)
            .appendTo($("#Province"));
    }
})
//赋值市级下拉框
function City() {
    if ($("#Province").find("option:selected").attr("val") == "0") {
        $("<option></option>")
            .val("地级市")
            .text("地级市")
            .attr("val", 0)
            .appendTo($("#City"));
        return;
    }
    $.ajax({
        async: false,
        type: "get",
        url: "/register/getCity?id=" + $("#Province").find("option:selected").attr("val"),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("<option></option>")
                    .text(data[i].city)
                    .attr("val", data[i].cityid)
                    .appendTo($("#City"));
            }
        }
    });
    console.log($("#Province").find("option:selected").attr("val"))
}
//赋值县级下拉框
function County() {
    if ($("#City").find("option:selected").attr("val") == "0") {
        $("<option></option>")
            .val("市、县级市、县")
            .text("市、县级市、县")
            .attr("val", 0)
            .appendTo($("#County"));
        return;
    }
    console.log($("#City").find("option:selected").attr("val"))
    $.ajax({
        async: false,
        type: "get",
        url: "/register/getArea?id="+ $("#City").find("option:selected").attr("val"),
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("<option></option>").text(data[i]).appendTo($("#County"));
            }
        }
    });
    console.log($("#City").find("option:selected").attr("val"))
}
//省市改变事件
$(function () {
    $("#Province").change(
        function () {
            $("#City").empty();
            City();
            $("#County").empty();
            County();
        }
    )
    $("#City").change(
        function () {
            $("#County").empty();
            County();
        }
    )
})
var result = {
    data: [
        [{"id": 1, "province": "北京市", "provinceid": "110000"},
            {"id": 2, "province": "天津市", "provinceid": "120000"}, {
            "id": 3,
            "province": "河北省",
            "provinceid": "130000"
        }, {"id": 4, "province": "山西省", "provinceid": "140000"},
            {"id": 5, "province": "内蒙古自治区", "provinceid": "150000"}, {
            "id": 6,
            "province": "辽宁省",
            "provinceid": "210000"
        }, {"id": 7, "province": "吉林省", "provinceid": "220000"},
            {"id": 8, "province": "黑龙江省", "provinceid": "230000"}, {
            "id": 9,
            "province": "上海市",
            "provinceid": "310000"
        }, {"id": 10, "province": "江苏省", "provinceid": "320000"},
            {"id": 11, "province": "浙江省", "provinceid": "330000"}, {
            "id": 12,
            "province": "安徽省",
            "provinceid": "340000"
        }, {"id": 13, "province": "福建省", "provinceid": "350000"},
            {"id": 14, "province": "江西省", "provinceid": "360000"}, {
            "id": 15,
            "province": "山东省",
            "provinceid": "370000"
        }, {"id": 16, "province": "河南省", "provinceid": "410000"},
            {"id": 17, "province": "湖北省", "provinceid": "420000"}, {
            "id": 18,
            "province": "湖南省",
            "provinceid": "430000"
        }, {"id": 19, "province": "广东省", "provinceid": "440000"},
            {"id": 20, "province": "广西壮族自治区", "provinceid": "450000"}, {
            "id": 21,
            "province": "海南省",
            "provinceid": "460000"
        }, {"id": 22, "province": "重庆市", "provinceid": "500000"},
            {"id": 23, "province": "四川省", "provinceid": "510000"}, {
            "id": 24,
            "province": "贵州省",
            "provinceid": "520000"
        }, {"id": 25, "province": "云南省", "provinceid": "530000"},
            {"id": 26, "province": "西藏自治区", "provinceid": "540000"}, {
            "id": 27,
            "province": "陕西省",
            "provinceid": "610000"
        }, {"id": 28, "province": "甘肃省", "provinceid": "620000"},
            {"id": 29, "province": "青海省", "provinceid": "630000"}, {
            "id": 30,
            "province": "宁夏回族自治区",
            "provinceid": "640000"
        }, {"id": 31, "province": "新疆维吾尔自治区", "provinceid": "650000"},
            {"id": 32, "province": "台湾省", "provinceid": "710000"}, {
            "id": 33,
            "province": "香港特别行政区",
            "provinceid": "810000"
        }, {"id": 34, "province": "澳门特别行政区", "provinceid": "820000"}]
    ]
}

