import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

//静态路由
const routes = [

    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/404',
        name: '404',
        component: () => import('../views/404.vue')
    },
    {
        path: '/videoDetail',
        name: 'VideoDetail',
        component: () => import('../views/VideoDetail')
    },


]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

// 提供一个重置路由的方法
export const resetRouter = () => {

    router["matcher"] = new VueRouter({

        mode: 'history',
        base: process.env.BASE_URL,
        routes
    })
}





//刷新也页面会重置路由
export const setRoutes = () => {
    const storeMenus = localStorage.getItem("menus");


        //获取当前的路由对象名称数组
        const currentRouteNames=router.getRoutes().map(v=>v.name)
    if (storeMenus) {
        // 拼装动态路由
        const manageRoute = {path: '/', name: 'Manage', component: () => import('../views/Manage.vue'), redirect: "/home", children: [
                { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
                { path: 'password', name: '修改密码', component: () => import('../views/Password.vue')}
            ]}
        const menus = JSON.parse(storeMenus)
        menus.forEach(item => {
            if(item.path){
                let itemMenu = {path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
                // @ts-ignore
                manageRoute.children.push(itemMenu)
            }else if(item.children.length){
                item.children.forEach(item=>{
                    if(item.path){
                        let itemMenu = {path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
                        // @ts-ignore
                        manageRoute.children.push(itemMenu)
                    }

                })
            }
        })

        if(!currentRouteNames.includes('Manage')){
            // 动态添加到现在的路由对象中去
            router.addRoute(manageRoute)

        }

    }

}


//重置路由，要再set一次
setRoutes()


// 路由守卫
router.beforeEach((to, from, next) => {
    if (to.name != null) {
        localStorage.setItem("currentPathName", to.name)
    }  // 设置当前的路由名称，为了在Header组件中去使用 // 设置当前的路由名称，为了在Header组件中去使用
    store.commit("setPath")  // 触发store的数据更新

    // 未找到路由的情况
    if (!to.matched.length) {
        const storeMenus = localStorage.getItem("menus")
        if (storeMenus) {
            next("/404")
        } else {
            // 跳回登录页面
            next("/login")
        }
    }
    // 其他的情况都放行
    next()

})

// 解决Vue-Router升级导致的Uncaught(in promise) navigation guard问题
// push
const originalPush = VueRouter.prototype.push
// @ts-ignore
VueRouter.prototype.push = function push (location, onResolve, onReject) {
    if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch(err => err)
}



// replace
const originalReplace = VueRouter.prototype.replace
VueRouter.prototype.replace= function replace(location) {
    return originalReplace.call(this, location).catch(err => err)
}
Vue.use(VueRouter)

export default router