import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import jQuery from 'jquery'
import 'popper.js'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
require('bootstrap')

import Notifications from '@kyvg/vue3-notification'

import VueLoading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';


// setup jquery
window.$ = window.jQuery = jQuery

// global variable visible to all Vue instances
// values come from the env.development or env.production files.
// Vue.prototype.$baseURL =  process.env.VUE_APP_BASE_URL + process.env.VUE_APP_ROOT_CONTEXT

console.log('App Base URL: ' + process.env.VUE_APP_BASE_URL)
console.log('Root Context: ' + process.env.VUE_APP_ROOT_CONTEXT)
//console.log('VUE_APP_GA_CODE:     ' + process.env.VUE_APP_GA_CODE)


const app = createApp(App)

// global variable visible to all Vue instances
// values come from the env.development or env.production files.
// Vue 3 way to keep globals
app.config.globalProperties.$baseURL = process.env.VUE_APP_BASE_URL + process.env.VUE_APP_ROOT_CONTEXT
console.log('Base URL:     ' + app.config.globalProperties.$baseURL)

app.use(Notifications)
app.use(router)
app.use(VueLoading);
app.mount('#app')
//createApp(App).use(router).mount('#app')
