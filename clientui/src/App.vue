<template>
  <div id="app">
     <notifications />
     <div class="header-div">
        <HeaderBar msg="Top"/>
        <SubHeaderBar msg="SubTop"/>
      </div>
       <div class="main-content">
          <!-- the router outlet, where all matched components would be viewed -->
          <router-view />

      </div>
    <FooterBar msg="Bottom"/>
  </div>
</template>

<script>

import HeaderBar from './components/HeaderBar.vue'
import SubHeaderBar from './components/SubHeaderBar.vue'
import FooterBar from './components/FooterBar.vue'

export default {
  name: 'app',
  props: {
  //   selectedTags:[]
  },
  components: {
    HeaderBar,
    SubHeaderBar,
    FooterBar,
  },

  metaInfo: {
    title: 'EVS Compare',
    headAttrs: {
        "X-XSS-Protection": 1,
        "mode": "block"
      },
    meta: [
      { name: 'Referrer-Policy', value: 'no-referrer | same-origin' },
    ]
  },
  data(){
    return {
      selectedTags:[]
    }
  }
}
</script>

<style>

</style>
