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
          <textarea rows="5" cols="40" v-model="text" :disabled="true"></textarea>
          <br>
          <text-reader @load="text = $event"
            @clearText= "onClearText">
          </text-reader>
        </div>
      </div>

      <div class="form-row container">
        <div class="form-group col-md-3">
          <label for="inputIncludes">Includes</label>
          <select :disabled="!this.advanced" id="inputIncludes" class="form-control" v-model="includeSelected">
            <option
              v-for="include in includes"
              :key="include">
              {{include}}
            </option>
          </select>
        </div>
        <div class="form-group col-md-3">
          <label for="inputType">Type</label>
          <select :disabled="!this.advanced" id="inputType" class="form-control" v-model="typeSelected">
            <option
              v-for="type in types"
              :key="type">
              {{type}}
            </option>
          </select>
        </div>
        <div class="form-group col-md-3">
          <label for="inputTerminology">Terminology</label>
          <select :disabled="!this.advanced" id="inputTerminology" class="form-control" v-model="terminologySelected">
            <option
              v-for="terminology in terminologies"
              :key="terminology">
              {{terminology}}
            </option>
          </select>
        </div>

        <div class="form-group col-md-3">
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
        </div>
      </div>

      <div class="collapse" id="collapseExample">
        <div class="form-row container">
          <!--div class="form-group col-md-3">
            <label for="inputAndOr">And/Or</label>
            <select id="inputAndOr" class="form-control">
              <option selected>And</option>
              <option>And</option>
              <option>Or</option>
            </select>
          </div-->
          <div class="form-group col-md-14">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="concept-status-tab" data-toggle="tab" href="#concept-status" role="tab" aria-controls="concept-status" aria-selected="concept-status">Concept Status</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="definition-source-tab" data-toggle="tab" href="#definition-source" role="tab" aria-controls="definition-source" aria-selected="false">Definition Source</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="definition-type-tab" data-toggle="tab" href="#definition-type" role="tab" aria-controls="definition-type" aria-selected="false">Definition Type</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="property-tab" data-toggle="tab" href="#property-type" role="tab" aria-controls="property-type" aria-selected="false">Property</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="synonym-term-group-tab" data-toggle="tab" href="#synonym-term-group-type" role="tab" aria-controls="synonym-term-group-type" aria-selected="false">Synonym Term Group</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="synonym-type-tab" data-toggle="tab" href="#synonym-type-type" role="tab" aria-controls="synonym-type-type" aria-selected="false">Synonym Type</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="synonym-source-tab" data-toggle="tab" href="#synonym-source-type" role="tab" aria-controls="synonym-source-type" aria-selected="false">Synonym Source</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="synonym-type-list-tab" data-toggle="tab" href="#synonym-type-list-type" role="tab" aria-controls="synonym-type-list-type" aria-selected="false">Synonym Type List</a>
              </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="concept-status" role="tabpanel" aria-labelledby="concept-status-tab">

                <br><br>
                <div class="container">
                  <h5 id="example-5">Concept Status</h5>
                  <!--div class="output">Data: {{ example5.value }}</div-->
                  <Multiselect
                    v-model="example5.value"
                    v-bind="example5"
                  ></Multiselect>

                  <br><br>
                </div>

              </div>
              <div class="tab-pane fade" id="definition-source" role="tabpanel" aria-labelledby="definition-source-tab">definition-source</div>
              <div class="tab-pane fade" id="definition-type" role="tabpanel" aria-labelledby="definition-type-tab">definition-type</div>
              <div class="tab-pane fade" id="property-type" role="tabpanel" aria-labelledby="property-type-tab">property-type</div>
              <div class="tab-pane fade" id="synonym-term-group-type" role="tabpanel" aria-labelledby="synonym-term-group-type-tab">synonym-term-group-type</div>
              <div class="tab-pane fade" id="synonym-type-type" role="tabpanel" aria-labelledby="synonym-type-type-tab">synonym-type</div>
              <div class="tab-pane fade" id="synonym-source-type" role="tabpanel" aria-labelledby="synonym-source-type-tab">synonym-source</div>
              <div class="tab-pane fade" id="synonym-type-list-type" role="tabpanel" aria-labelledby="synonym-type-list-type-tab">synonym-type-list</div>
            </div>

        </div>

        </div>
      </div>
    </form>

    <!--div class="form-group col-md-3">
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

    <div class="form-group col-md-3">
      <button
        id="search"
        class="btn btn-primary"
        type="button"
        :disabled='(searchByText && searchText.length == 0) || (!searchByText && text.length == 0)'
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
import { useLoading } from 'vue3-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';
import { notify } from "@kyvg/vue3-notification";
import Multiselect from '@vueform/multiselect'
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

      text: "",

      searchByText: true,

      example5: {
        mode: 'tags',
        value: ['concept status 1'],
        closeOnSelect: false,
        options: [
          { value: 'concept status 1', label: 'concept status 1' },
          { value: 'concept status 2', label: 'concept status 2' },
          { value: 'concept status 3', label: 'concept status 3' },
          { value: 'concept status 4', label: 'concept status 4' },
          { value: 'concept status 5', label: 'concept status 5' },
          { value: 'concept status 6', label: 'concept status 6' },
          { value: 'concept status 7', label: 'concept status 7' },
          { value: 'concept status 8', label: 'concept status 8' },
        ],
        searchable: true,
        createTag: true
      },
    }

  },
  props: {
    msg: String
  },
  components: {
      TextReader,
      Multiselect,
  },
  metaInfo: {
    title: 'NCIt Search',
  },
  methods: {

    onClearText: function() {
      this.text=''
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


        console.log(this.text)
        notify({
          title: "Searching",
          text: "Searching and gathering results. Please wait.",
          speed: 3000,
          type: "success"
        });

        // If the file has returns, convert them to commas
        let trimmedFileText = this.text.split(/\r?\n|\r/g).join(',')
        console.log(trimmedFileText)


        let loader = useLoading();
        loader.show({
          // Optional parameters
          container: this.fullPage ? null : this.formContainer.value,
          canCancel: false,
          onCancel: this.onCancel,
        });

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
        }).finally(function() { loader.hide()});
        // setTimeout(() => {
        //     loader.hide()
        //   },3000)

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
