<script>
    // 定义一些公共的属性和方法
    const API_ROOT = "http://127.0.0.1:8080/api"
    // const API_ROOT = "http://114.116.235.167:8080"
    function commonFun() {
        console.log("公共方法")
    }
    // 暴露出这些属性和方法
    export default {
        API_ROOT,
        commonFun
    }
</script>
