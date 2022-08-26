import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import store from './store'

import request from '@/utils/request.js';

Vue.config.productionTip = false

Vue.use(ElementUI, { size: "mini" });

Vue.prototype.request=request



// @ts-ignore
new Vue({
  router,
  // @ts-ignore
  store,
  render: h => h(App)
}).$mount('#app')

