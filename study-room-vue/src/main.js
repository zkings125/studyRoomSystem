import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
// import Authority from './components/Authority.vue'
import Layout from './components/Layout.vue'
import CustomerTable from './components/CustomerTable.vue'
import UpdateCustomerInfo from './components/UpdateCustomerInfo.vue'
import StudyroomTable from './components/StudyroomTable.vue'
import UpdateStudyroomInfo from './components/UpdateStudyroomInfo.vue'
import AdminTable from './components/AdminTable.vue'
import UpdateAdmin from './components/UpdateAdmin.vue'
import AddAdmin from './components/AddAdmin.vue'
import AddStudyroom from './components/AddStudyroom.vue'
import UserInfo from './components/UserInfo.vue'
import UpdatePassword from './components/UpdatePassword.vue'
import Logout from './components/Logout.vue'
import Common from './components/Common.vue'


Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.prototype.GLOBAL = Common

const routes = [
  {
    path:"/",
    component: Layout,
    redirect:"./customer_table",//页面重定向
    children:[
      {
        path:"customer_table",
        components:{
          content:CustomerTable
        }
      },
      {
        path:"update_customer_info",
        components:{
          content:UpdateCustomerInfo
        }
      },
      {
        path:"studyroom_table",
        components:{
          content:StudyroomTable
        }
      },
      {
        path:"update_studyroom_info",
        components:{
          content:UpdateStudyroomInfo
        }
      },
      {
        path:"admin_table",
        components:{
          content:AdminTable
        }
      },
      {
        path:"update_admin",
        components:{
          content:UpdateAdmin
        }
      },
      {
        path:"add_admin",
        components:{
          content:AddAdmin
        }
      },
      {
        path:"add_studyroom",
        components:{
          content:AddStudyroom
        }
      },
      {
        path:"userinfo",
        components:{
          content:UserInfo
        }
      },
      {
        path:"update_password",
        components:{
          content:UpdatePassword
        }
      },
      {
        path:"logout",
        components:{
          content:Logout
        }
      },
    ]

  }

]

const router = new VueRouter({
  routes
})


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
