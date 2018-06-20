import {maxLength, minLength, required} from "vuelidate/lib/validators/index";
import {validationMixin} from "vuelidate";
import {
  MAX_NAME_LENGTH, MAX_SURNAME_LENGTH, MIN_NAME_LENGTH,
  MIN_SURNAME_LENGTH, NameValidator
} from "../../../_validator/index";
import {getErrorMessage, UNEXPECTED} from "../../../_sys/json-errors";

export default {
  name: "index-page",
  mixins: [validationMixin],
  data: () => ({
    showStudentDialog: false,
    showSubjectDialog: false,
    groupId: 0,
    counter: 0,
    groups: [],
    students: [],
    teachers: [],
    subjectsInfo: [],
    teacherId: 0,
    form: {
      name: null,
      surname: null,
      middleName: null
    },
    form2: {
      subjectName: null,
    },
    sending: false,
    errors: null,
    showStudentSnackBar: false,
    showSubjectSnackBar: false
  }),
  validations: {
    form: {
      name: {
        required,
        minLength: minLength(MIN_NAME_LENGTH),
        maxLength: maxLength(MAX_NAME_LENGTH),
        NameValidator
      },
      surname: {
        required,
        minLength: minLength(MIN_SURNAME_LENGTH),
        maxLength: maxLength(MAX_SURNAME_LENGTH),
        NameValidator
      },
      middleName: {
        required,
        minLength: minLength(MIN_SURNAME_LENGTH),
        maxLength: maxLength(MAX_SURNAME_LENGTH),
        NameValidator
      }
    },
    form2: {
      subjectName: {
        required
      }
    }
  },
  methods: {
    getValidationClass(fieldName) {
      const field = this.$v.form[fieldName];

      if (field) {
        return {
          'md-invalid': field.$invalid && field.$dirty
        };
      }
    },

    getValidationClass2(fieldName) {
      const field = this.$v.form2[fieldName];

      if (field) {
        return {
          'md-invalid': field.$invalid && field.$dirty
        };
      }
    },

    addStudent() {
      this.errors = [];
      this.sending = true;

      this.$http.post('students',
        {
          name: this.form.name,
          surname: this.form.surname,
          middleName: this.form.middleName,
          groupId: this.groupId
        }).then(
        response => {
          let json = response.body;

          if (!json.errors) {
            this.showStudentDialog = false;
            this.showStudentSnackBar = true;

          } else if (json.errors.length) {
            this.errors = this.errors.concat(json.errors.map((error) => getErrorMessage(error)));
          } else {
            this.errors.push(getErrorMessage(UNEXPECTED));
          }

          this.sending = false;
        }, error => {
          switch (error.status) {
            case 400:
            case 500:
              let json = error.body;

              if (json.errors) {
                this.errors = this.errors.concat(json.errors.map((error) => getErrorMessage(error)));
              }
              break;
          }

          if (!this.errors.length) {
            this.errors.push('HTTP error (' + error.status + ': ' + error.statusText + ')');
          }

          this.sending = false;
        }
      )
    },
    addSubject() {
      this.errors = [];
      this.sending = true;

      this.$http.post('subjects',
        {
          subjectName: this.form2.subjectName,
          groupId: this.groupId,
          teacherId: this.teacherId
        }).then(
        response => {
          let json = response.body;

          if (!json.errors) {
            this.showSubjectDialog = false;
            this.showSubjectSnackBar = true;

          } else if (json.errors.length) {
            this.errors = this.errors.concat(json.errors.map((error) => getErrorMessage(error)));
          } else {
            this.errors.push(getErrorMessage(UNEXPECTED));
          }

          this.sending = false;
        }, error => {
          switch (error.status) {
            case 400:
            case 500:
              let json = error.body;

              if (json.errors) {
                this.errors = this.errors.concat(json.errors.map((error) => getErrorMessage(error)));
              }
              break;
          }

          if (!this.errors.length) {
            this.errors.push('HTTP error (' + error.status + ': ' + error.statusText + ')');
          }

          this.sending = false;
        }
      )
    },
    validateStudent() {
      this.$v.form.$touch();

      if (!this.$v.form.$invalid) {
        this.addStudent();
      }
    },

    validateSubject() {
      this.$v.form2.$touch();

      if (!this.$v.form2.$invalid) {
        this.addSubject();
      }
    }
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

    this.$http.get('students')
      .then(response => {
        let json = response.body;

        if (!json.errors) {
          this.students = json.data
        }
      }, error => {
        console.log(JSON.stringify(error.body));
      });

    this.$http.get('teachers')
      .then(response => {
        let json = response.body;

        if (!json.errors) {
          this.teachers = json.data
        }
      }, error => {
        console.log(JSON.stringify(error.body));
      });

    this.$http.get('subjectsInfo')
      .then(response => {
        let json = response.body;

        if (!json.errors) {
          this.subjectsInfo = json.data
        }
      }, error => {
        console.log(JSON.stringify(error.body));
      });
  }
}
