let provinces;
let cities;
let countries;

/**
 * 初始化省市区数据
 */
function initDate() {
    //创建对象
    let beijingProvince = new Object();
    //设置省id 省名称
    beijingProvince.provinceId = 1
    beijingProvince.provinceName = "北京"

    console.log(beijingProvince)
    let beijingCity = new Object()
    //设置市id 市名称
    beijingCity.provinceId = 1
    beijingCity.cityId = 2
    beijingCity.cityName = "北京"

    console.log(beijingCity)
    let chaoYang = new Object()
    //设置区id 区名称
    chaoYang.provinceId = 1
    chaoYang.cityId = 2
    chaoYang.countryId = 1
    chaoYang.countryName = "朝阳区"

    console.log(chaoYang)

    //创建对象
    let beijing1Province = new Object();
    //设置省id 省名称
    beijing1Province.provinceId = 3
    beijing1Province.provinceName = "上海"
    let beijing1City = new Object()

    //设置市id 省名称
    beijing1City.provinceId = 3
    beijing1City.cityId = 1
    beijing1City.cityName = "上海"
    let chaoYang1 = new Object()

    //设置区id 省名称
    chaoYang1.provinceId = 3
    chaoYang1.cityId = 1
    chaoYang1.countryId = 1
    chaoYang1.countryName = "黄埔"

    provinces = [beijingProvince, beijing1Province]
    cities = [beijingCity, beijing1City]
    countries = [chaoYang, chaoYang1]


    // console.log("省id:" + provinces[0].provinceId);
    // console.log("省name:" + provinces[0].provinceName);
    // console.log("市:" + cities[0]);
    // console.log("区县:" + countries[0]);

}

//初始化 并 添加省份
window.onload = function () {
    initDate();

    let provinceSelect = document.getElementById('provinceSelect');
//      将对象绑定option标签
    for (let i = 0; i < provinces.length; i++) {
        //创建省份option标签
        let optionElement = document.createElement("option");
        //将省份数组中的数据赋值给新建的标签  value属性(省份id) 文本
        optionElement.setAttribute("value", provinces[i].provinceId)
        optionElement.innerText = provinces[i].provinceName
        //将标签添加进select标签
        provinceSelect.append(optionElement)
    }
}

//省份发生变化时 触发该事件  根据城市添加城市
function getCities(provinceId) {
    console.log("value用户选择省份id:" + provinceId)

    let citiesSelect = document.getElementById('citiesSelect');
    //清空之前添加的选项
    citiesSelect.innerHTML = "<option value=\"0\" selected>请选择城市</option>"

    //添加城市
    for (let i = 0; i < cities.length; i++) {
        //将省份id相同的城市添加进 select
        if (provinceId == cities[i].provinceId) {
            console.log("城市id:" + cities[i].cityId)
            console.log("城市name:" + cities[i].cityName)
            //创建option标签
            let optionElement = document.createElement("option");

            //将满足条件的城市 的数据赋值给新建的标签的 value属性(id) 文本
            optionElement.setAttribute("value", cities[i].cityId)
            optionElement.innerText = cities[i].cityName
            //将标签添加进select标签
            citiesSelect.append(optionElement)
        }
    }
}

/**
 * 根据省份和城市id获取区县
 */
function getCountries(cityId) {
    console.log("用户选择的城市id:" + cityId)

    //获取下拉框选中值 省id
    let province = document.getElementById("provinceSelect");

    //获取当前下拉框选中值得索引
    let selectedIndex = province.selectedIndex;
    let provinceId = province.options[selectedIndex].value;
    console.log("选择省份id:" + provinceId)

    console.log("选择省份为:" + province.options[selectedIndex].innerText)

    let countriesSelect = document.getElementById('countriesSelect');
    //清空之前添加的选项
    countriesSelect.innerHTML = "<option value=\"0\" selected>请选择区县</option>"

    //添加区县
    for (let i = 0; i < countries.length; i++) {
        //将当前省份下拉框选中值的省id 以及 城市下拉框的城市ID 相同 区县添加进 select
        if (provinceId == countries[i].provinceId && cityId == countries[i].cityId) {

            //创建option标签
            let optionElement = document.createElement("option");
            //将满足条件的城市 的数据赋值给新建的标签的 value属性 文本
            optionElement.setAttribute("value", countries[i].countryId)
            optionElement.innerText = countries[i].countryName
            //将标签添加进select标签
            countriesSelect.append(optionElement)
        }
    }
}
