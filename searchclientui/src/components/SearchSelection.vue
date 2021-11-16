<template>
  <div id="Search">
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
export default {
  name: 'Search'
}
</script>
<!-- styling for the component -->
<style>
#about {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
