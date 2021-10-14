//根据传递过来的参数name获取对应的值
function getPatemeter(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i")
    let value = location.search.substr(1).match(reg);
    if (name != null) return (value[2]);
    return null;
}