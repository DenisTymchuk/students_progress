import {validationMixin} from "vuelidate";

export default {
  name: "index-page",
  mixins: [validationMixin],
  data: () => ({
    groups: [],
    subjectsInfo: [],
  }),
  validations: {},
  methods: {
  },
  created: function() {
    this.$http.get('groups')
      .then(response => {
        let json = response.body;

        if (!json.errors) {
          this.groups = json.data
        }
      }, error => {
        console.log(JSON.stringify(error.body));
      });

    this.$http.get('subjects/modules')
      .then(response => {
        let json = response.body;

        console.log(response)

        if (!json.errors) {
          this.subjectsInfo = json.data;
        }
      }, error => {
        console.log(JSON.stringify(error.body));
      });
  }
}
