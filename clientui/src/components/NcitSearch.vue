<template>

  <div id="ncit-search" class="container">

    <div class="instructionsContainer">
      <br>
      <nav class="navbar navbar-light bg-light">
        <span class="navbar-text">
          <p>Select "Search Text" to search with text that you enter.  Select "Load File" to load a .csv file that contains your search criteria.</p>
          <p>"Includes", "Type" and "Terminology" are disabled for the default, simple search</p>
        </span>
      </nav>
      <br>
    </div>

    <form>
      <div class="form-row container">
        <div class="form-group col-md-3">
          <div class="btn-group btn-group-toggle form-group" data-toggle="buttons">
            <label class="btn btn-secondary btn-sm active">
              <input type="radio" name="options" id="searchText" autocomplete="off" checked v-on:click="searchByText=true"> Search Text
            </label>
            <label class="btn btn-secondary btn-sm">
              <input type="radio" name="options" id="searchFile" autocomplete="off" v-on:click="searchByText=false"> Load File
            </label>
          </div>
        </div>
      </div>

      <div class="form-row container">
        <!-- Search by Text -->
        <div v-if="searchByText" class="form-group col-md-12">
          <label for="inputSearchText">Search Text</label>
          <input type="text" class="form-control" v-model="searchText" id="inputSearchText">
        </div>

        <!-- Search by uploading a file -->
        <div v-if="!searchByText" class="form-group col-md-12">
          <textarea rows="5" cols="40" v-model="textFromFile" :disabled="true"></textarea>
          <br>
          <text-reader @load="textFromFile = $event"
            @clearText= "onClearText">
          </text-reader>
        </div>
      </div>

      <div class="form-row container">
        <div class="form-group col-md-4">
          <label for="inputIncludes">Includes</label>
          <select :disabled="!this.advanced" id="inputIncludes" class="form-control" v-model="includeSelected">
            <option
              v-for="include in includes"
              :key="include">
              {{include}}
            </option>
          </select>
        </div>
        <div class="form-group col-md-4">
          <label for="inputType">Type</label>
          <select :disabled="!this.advanced" id="inputType" class="form-control" v-model="typeSelected">
            <option
              v-for="type in types"
              :key="type">
              {{type}}
            </option>
          </select>
        </div>
        <div class="form-group col-md-4">
          <label for="inputTerminology">Terminology</label>
          <select :disabled="!this.advanced" id="inputTerminology" class="form-control" v-model="terminologySelected">
            <option
              v-for="terminology in terminologies"
              :key="terminology">
              {{terminology}}
            </option>
          </select>
        </div>

        <!-- Advanced Search Button -->
        <!--div class="form-group col-md-3">
          <label for="searchButton">Simple/Advanced Search</label>
          <button
            id="advancedSearch"
            class="btn btn-primary"
            type="button"
            data-toggle="collapse"
            data-target="#collapseExample"
            aria-expanded="false"
            aria-controls="collapseExample"
            v-on:click="toggleButtonText()">
            {{button.text}}
          </button>
        </div-->
      </div>

      <!--div class="collapse" id="collapseExample">
        <div class="form-row container">
          Advanced search here...
        </div>
      </div-->
    </form>

    <div class="form-group col-md-3">
      <button
        id="search"
        class="btn btn-primary"
        type="button"
        :disabled='(searchByText && searchText.length == 0) || (!searchByText && textFromFile.length == 0)'
        v-on:click="search()">
        Search
      </button>
    </div>

  </div>
</template>

<script>

import { ref } from 'vue';
import api from '../api.js'
import axios from 'axios'
// import { useLoading } from 'vue3-loading-overlay';
// import 'vue-loading-overlay/dist/vue-loading.css';
import { notify } from "@kyvg/vue3-notification";
import TextReader from "./TextReader";

export default {
  name: 'NCIt Search',

  data() {
    return {
      filename: 'search_results',
      extension: "txt",
      fullPage: ref(true),
      formContainer: ref(null),

      button: {
        text: 'Advanced Search >>'
      },
      searchText: "",
      advanced: false,
      includes: [],
      includeSelected: "minimal",
      types: [],
      typeSelected: "contains",
      terminologies: [],
      terminologySelected: "ncit",

      textFromFile: "",
      searchByText: true,
    }

  },
  props: {
    msg: String
  },
  components: {
      TextReader,
  },
  metaInfo: {
    title: 'NCIt Search',
  },
  methods: {

    onClearText: function() {
      this.textFromFile=''
    },
    toggleButtonText: function() {
      this.advanced = !this.advanced;
      this.button.text = this.advanced ? '<< Simple Search' : 'Advanced Search >>'

      this.setSearchCriteria()
    },
    setSearchCriteria: function() {
      if (!this.advanced) {
        this.includeSelected = "minimal"
        this.typeSelected = "contains"
        this.terminologySelected = "ncit"
      }
    },

    search: function() {
      console.log("Search Called.\n" +
        "SearchText : " + this.searchText + "\n" +
        "Includes : " + this.includeSelected  + "\n" +
        "Type : " + this.typeSelected + "\n" +
        "Terminology : " + this.terminologySelected)

        console.log(this.textFromFile)
        notify({
          title: "Searching",
          text: "Searching and gathering results. Please wait.",
          speed: 2000,
          type: "success"
        });

        // If the file has returns, convert them to commas
        let trimmedFileText = this.textFromFile.split(/\r?\n|\r/g).join(',')
        console.log(trimmedFileText)

        let searchUrl=""
        if (this.searchByText) {
          searchUrl = this.$baseURL +'/download/get-advancedfile-for-extended-search/'  +
              this.terminologySelected + '/' +
              this.includeSelected + '/' +
              this.typeSelected  + '/'+
              this.searchText
        }
        else {
          searchUrl = this.$baseURL +'/download/get-advancedfile-for-extended-search-multiterm/'  +
              this.terminologySelected + '/' +
              this.includeSelected + '/' +
              this.typeSelected  + '/'+
              trimmedFileText
        }
        console.log(
          searchUrl
        )

        // const $loading = useLoading();
        let loader = this.$loading.show({
          // Optional parameters
          container: this.fullPage ? null : this.formContainer.value,
          canCancel: false,
          onCancel: this.onCancel,
        });

        axios({
            url: searchUrl,
            method: 'GET',
            responseType: 'blob',
        }).then((response) => {
              var fileURL = window.URL.createObjectURL(new Blob([response.data]));
              var fileLink = document.createElement('a');

              fileLink.href = fileURL;
              fileLink.setAttribute('download', this.filename + '.' + this.extension);
              document.body.appendChild(fileLink);
              fileLink.click();
        }).catch(function(error) {
              console.error("Download Error: " + error);
              alert("Error Downloading file");
        }).finally(function() {
          loader.hide();
        });
    }
  },

  created() {
      // scroll to the top of the page
      window.scrollTo(0,0);

      // load the includes dropdown after the page is loaded.
      api.getOptionIncludes(this.$baseURL)
          .then((data)=>{this.includes = data;
      })
      // load the type dropdown after the page is loaded.
      api.getOptionType(this.$baseURL)
          .then((data)=>{this.types = data;
      })
      // load the terminology dropdown after the page is loaded.
      api.getOptionTerminologies(this.$baseURL)
          .then((data)=>{this.terminologies = data;
      })
    }
}

</script>

<!-- styling for the component -->

<style src="@vueform/multiselect/themes/default.css">


.instructionsContainercontainer {
 padding: 125px 50px 75px;
}
/* .container{
  padding: 45px 15px 5px 15px;
} */

.advancedSearch {
  background-color: rgb(0, 125, 188);
  border-color: rgb(0, 125, 188);
  color: white;
}
.btn-primary:hover, .btn-primary:focus, .btn-primary:active, .btn-primary.active, .open>.dropdown-toggle.btn-primary {
  background-color: rgb(0, 125, 188) !important;
	border-color: rgb(0, 125, 188) !important;
	color: yellow;
}
.btn-link {
	color: rgba(33, 37, 41, 1) !important;
}

a.disabled {
	color: #767676 !important;
}
.active {
	color: rgb(0, 125, 188) !important;
}

</style>
