<template>
  <div id="Search">

    <!-- Modal (pop up) dialog to display warning -->
    <Modal
      v-show="isModalVisible"
      @close="closeModal">
      
      <template v-slot:header>
        Warning
      </template>

      <template v-slot:body>
         <p>You are accessing a US Government web site which may
            contain information that must be protected under the US Privacy Act
            or other sensitive information and is intended for Government
            authorized use only.</p>
         <p>Unauthorized attempts to upload information, change information,
            or use of this web site may result in disciplinary action, civil,
            and/or criminal penalties. Unauthorized users of this website
            should have no expectation of privacy regarding any communications
            or data processed by this website.</p>
         <p>Anyone accessing this website expressly consents to monitoring of
            their actions and all communications or data transiting or stored
            on related to this website and is advised that if such monitoring
            reveals possible evidence of criminal activity, NIH may provide
            that evidence to law enforcement officials.</p>
      </template>

      <template v-slot:footer>
      </template>
    </Modal>

    <div class="title-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      <h2 class="display-4">NCI Search</h2>
      <p class="lead">The NCI Thesaurus (NCIt) provides reference terminology services for the National Cancer Institute and other institutional users. The EVS Search tool provides....</p>
    </div>

    <div class="container">
      <div class="card-deck mb-3 text-center">
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h5 class="my-0 font-weight-normal"><b>EVS Compare</b></h5>
          </div>
          <div class="card-body d-flex flex-column">
            <h3 class="card-title pricing-card-title">Search...</h3>
            <ul class="list-unstyled mt-3 mb-4">
              <li>Search and download</li>
            </ul>
            <router-link v-bind:to="'/ncitsearch'" class="mt-auto">
              <button type="button" class="align-self-end btn btn btn-block btn-primary selectButton">Start Searching</button>
            </router-link>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import Modal from "@/components/Modal.vue";
  import { useCookies } from "vue3-cookies";

  export default {
    name: 'Search Selection',

    components: {
      Modal,
    },
    setup() {
      const { cookies } = useCookies();
      return { cookies };
    },

    data() {
      return {
        warningCookie: "NCIEVSCompareWarningAcknowledgement",
        isModalVisible: false,
      };
    },
    metaInfo: {
      title: 'EVS Compare - Search Selection',
    },
    methods: {
      showModal() {
        var tempWarningCookie = this.cookies.get(this.warningCookie);
        if (!tempWarningCookie) {
          this.isModalVisible = true;
        }
      },
      closeModal() {
        this.isModalVisible = false;
        this.setWarningCookie()
      },
      setWarningCookie() {
        this.cookies.set(this.warningCookie,"true");
      },
    },
    mounted() {
      let my_cookie_value = this.cookies.get("myCoookie");
      console.log(my_cookie_value);
      this.cookies.set("myCoookie", "abcdefg");
    },
    created() {
      // scroll to the top of the page
      window.scrollTo(0,0);
      this.showModal()
    },

  }
</script>

<!-- styling for the component -->
<style lang="scss" scoped>

html {
  font-size: 14px;
}
@media (min-width: 768px) {
  html {
    font-size: 16px;
  }
}

a:hover {
  text-decoration: none;
}

.container {
  max-width: 960px;
}

.title-header {
  max-width: 700px;
}

.card-deck .card {
  min-width: 200px;
}

.border-top { border-top: 1px solid #e5e5e5; }
.border-bottom { border-bottom: 1px solid #e5e5e5; }
.box-shadow { box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05); }

.selectButton {
  background-color: rgb(0, 125, 188);
  border-color: rgb(0, 125, 188);
  color: white;
}

.btn-primary:hover, .btn-primary:focus, .btn-primary:active, .btn-primary.active, .open>.dropdown-toggle.btn-primary {
  background-color: rgb(0, 125, 188) !important;
  border-color: rgb(0, 125, 188) !important;
  color: white;
}
.button {
  background-color: rgb(0, 125, 188);
  border-color: rgb(0, 125, 188);
  color: white;
}
</style>
