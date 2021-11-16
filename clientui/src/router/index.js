import { createWebHistory, createRouter } from "vue-router";
import SearchSelection from "@/components/SearchSelection.vue";
import NcitSearch from "@/components/NcitSearch";
import About from "@/components/About.vue";

const routes = [
  {
    path: "/",
    name: "Search Selection",
    component: SearchSelection,
  },
  {
    path: "/ncitsearch",
    name: "NCIt Search",
    component: NcitSearch,
  },
  {
    path: "/about",
    name: "About",
    component: About,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
