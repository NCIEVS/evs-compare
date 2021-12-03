import { createWebHistory, createRouter } from "vue-router";
import SearchSelection from "@/components/SearchSelection.vue";
import NcitSearch from "@/components/NcitSearch";
import Documentation from "@/components/Documentation.vue";
import Home from "@/components/Home.vue";

const routes = [
  {
    path: "/",
    // name: "Search Selection",
    // component: SearchSelection,
    name: "Home",
    component: Home,
  },
  {
    path: "/selection",
    name: "Search Selection",
    component: SearchSelection,
  },
  {
    path: "/ncitsearch",
    name: "NCIt Search",
    component: NcitSearch,
  },
  {
    path: "/documentation",
    name: "Documentation",
    component: Documentation,
  },

  // if page is unknown, show main selection page.
  {
    path: '/:pathMatch(.*)*',
    //component: SearchSelection
    redirect: "/selection",
  },
];
console.log("in router.... base: " + process.env.VUE_APP_ROOT_CONTEXT)
const router = createRouter({
  history: createWebHistory(process.env.VUE_APP_ROOT_CONTEXT),
  //base: process.env.VUE_APP_ROOT_CONTEXT,

  routes,
});

export default router;
