<template>
  <div id="documentation">
    <div class="container documentation-container">
      <div class="row">
          <div class="col-lg-4">
              <div class="nav nav-pills faq-nav" id="faq-tabs" role="tablist" aria-orientation="vertical">
                  <a href="#tab1" class="nav-link active" data-toggle="pill" role="tab" aria-controls="tab1" aria-selected="true">
                      <i class="mdi mdi-help-circle"></i> EVS Compare Tool
                  </a>
                  <a href="#tab2" class="nav-link" data-toggle="pill" role="tab" aria-controls="tab2" aria-selected="false">
                      <i class="mdi mdi-account-settings"></i> NCIt Search
                  </a>

              </div>
          </div>
          <div class="col-lg-8">
              <div class="tab-content" id="faq-tab-content">
                  <div class="tab-pane show active" id="tab1" role="tabpanel" aria-labelledby="tab1">
                      <div class="accordion" id="accordion-tab-1">
                          <div class="card">
                              <div class="card-header" id="accordion-tab-1-heading-1">
                                  <h5>
                                      <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#accordion-tab-1-content-1" aria-expanded="false" aria-controls="accordion-tab-1-content-1">
                                        What is the EVS Compare Tool
                                      </button>
                                  </h5>
                              </div>
                              <div class="collapse show" id="accordion-tab-1-content-1" aria-labelledby="accordion-tab-1-heading-1" data-parent="#accordion-tab-1">
                                  <div class="card-body">
                                    <p>
                                      The EVS Compare Tool provides ...
                                    </p>
                                    <p>
                                      The EVS Compare Tool currently provides one simple search.
                                    </p>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="tab-pane" id="tab2" role="tabpanel" aria-labelledby="tab2">
                      <div class="accordion" id="accordion-tab-2">
                          <div class="card">
                              <div class="card-header" id="accordion-tab-2-heading-1">
                                  <h5>
                                      <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#accordion-tab-2-content-1" aria-expanded="false" aria-controls="accordion-tab-2-content-1">
                                        What is the "Search Text" Option?
                                      </button>
                                  </h5>
                              </div>
                              <div class="collapse show" id="accordion-tab-2-content-1" aria-labelledby="accordion-tab-2-heading-1" data-parent="#accordion-tab-2">
                                  <div class="card-body">
                                    <p>
                                        This option allows the user to enter text to search for.
                                    </p>
                                  </div>
                              </div>
                          </div>
                          <div class="card">
                              <div class="card-header" id="accordion-tab-2-heading-2">
                                  <h5>
                                      <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#accordion-tab-2-content-2" aria-expanded="false" aria-controls="accordion-tab-2-content-2">
                                        What is the "Load File" Option?
                                      </button>
                                  </h5>
                              </div>
                              <div class="collapse" id="accordion-tab-2-content-2" aria-labelledby="accordion-tab-2-heading-2" data-parent="#accordion-tab-2">
                                  <div class="card-body">
                                    <p>
                                      This option alsos the user to browse for and select a file to use for comparing.
                                    </p>
                                    <p>
                                      The file should have a return after every line that is to be searched.
                                    </p>

                                  </div>
                              </div>
                          </div>

                      </div>
                  </div>
            </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import 'popper.js'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'



  export default {
    name: 'documentation',

  metaInfo: {
    title: 'EVS Compare - Documentation',
  },
  created() {
    // scroll to the top of the page
    window.scrollTo(0,0);
    }
}
</script>
<!-- styling for the component -->
<style>



.documentation-container{
  padding: 45px 15px 45px 15px;
}

</style>
