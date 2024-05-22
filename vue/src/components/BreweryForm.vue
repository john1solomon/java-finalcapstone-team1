<template>
  <form class="brewery-form" @submit.prevent="submitForm">

    <div class="brewery-form-info">

      <label for="breweryName">Brewery Name</label>
      <input id="breweryName" v-model="editbrewery.breweryName" type="text" required>

      <label for="brewerUsername">Brewer Username</label>
      <input id="brewerUsername" v-model="editbrewery.brewerUsername" type="text" required>

      <label for="contactInformation">Contact Information</label>
      <input id="contactInformation" v-model="editbrewery.contactInformation" type="text">

      <label for="streetAddress">Street Address</label>
      <input id="streetAddress" v-model="editbrewery.streetAddress" type="text">

      <label for="city">City</label>
      <input id="city" v-model="editbrewery.city" type="text">

      <label for="stateCode">State</label>
      <div>
        <!-- <input id="stateCode" v-model="editbrewery.stateCode" type="text" maxlength="2"> -->
        <select id="stateCode" v-model="editbrewery.stateCode">
          <option value="AL">Alabama (AL)</option>
          <option value="AK">Alaska (AK)</option>
          <option value="AZ">Arizona (AZ)</option>
          <option value="AR">Arkansas (AR)</option>
          <option value="CA">California (CA)</option>
          <option value="CO">Colorado (CO)</option>
          <option value="CT">Connecticut (CT)</option>
          <option value="DE">Delaware (DE)</option>
          <option value="DC">District Of Columbia (DC)</option>
          <option value="FL">Florida (FL)</option>
          <option value="GA">Georgia (GA)</option>
          <option value="HI">Hawaii (HI)</option>
          <option value="ID">Idaho (ID)</option>
          <option value="IL">Illinois (IL)</option>
          <option value="IN">Indiana (IN)</option>
          <option value="IA">Iowa (IA)</option>
          <option value="KS">Kansas (KS)</option>
          <option value="KY">Kentucky (KY)</option>
          <option value="LA">Louisiana (LA)</option>
          <option value="ME">Maine (ME)</option>
          <option value="MD">Maryland (MD)</option>
          <option value="MA">Massachusetts (MA)</option>
          <option value="MI">Michigan (MI)</option>
          <option value="MN">Minnesota (MN)</option>
          <option value="MS">Mississippi (MS)</option>
          <option value="MO">Missouri (MO)</option>
          <option value="MT">Montana (MT)</option>
          <option value="NE">Nebraska (NE)</option>
          <option value="NV">Nevada (NV)</option>
          <option value="NH">New Hampshire (NH)</option>
          <option value="NJ">New Jersey (NJ)</option>
          <option value="NM">New Mexico (NM)</option>
          <option value="NY">New York (NY)</option>
          <option value="NC">North Carolina (NC)</option>
          <option value="ND">North Dakota (ND)</option>
          <option value="OH">Ohio (OH)</option>
          <option value="OK">Oklahoma (OK)</option>
          <option value="OR">Oregon (OR)</option>
          <option value="PA">Pennsylvania (PA)</option>
          <option value="RI">Rhode Island (RI)</option>
          <option value="SC">South Carolina (SC)</option>
          <option value="SD">South Dakota (SD)</option>
          <option value="TN">Tennessee (TN)</option>
          <option value="TX">Texas (TX)</option>
          <option value="UT">Utah (UT)</option>
          <option value="VT">Vermont</option>
          <option value="VA">Virginia</option>
          <option value="WA">Washington</option>
          <option value="WV">West Virginia</option>
          <option value="WI">Wisconsin</option>
          <option value="WY">Wyoming</option>
        </select>

        <label for="postalCode">Postal Code</label>
        <input id="postalCode" v-model="editbrewery.postalCode" type="text" maxlength="10">
      </div>

    </div>

    <div class="brewery-form-data">

      <label for="logoFilename">Logo File</label>
      <div class="logoFileUpload">
        <input id="logoFilename" type="file" @change="handleFileChange($event)">
      </div>

      <label for="breweryURL">Brewery URL</label>
      <input id="breweryURL" v-model="editbrewery.breweryURL" type="text">

      <label for="menuURL">Menu URL</label>
      <input id="menuURL" v-model="editbrewery.menuURL" type="text">

      <label for="ocbaInfoURL">OCBA Info URL</label>
      <input id="ocbaInfoURL" v-model="editbrewery.ocbaInfoURL" type="text">

      <label for="mapURL">Map URL</label>
      <input id="mapURL" v-model="editbrewery.mapURL" type="text">

    </div>

    <div class="actions">
      <button class="btn-submit" type="submit">Save</button>
      <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
    </div>

</form>
</template>

<script>

import breweryService from '../services/BreweryService.js';

export default {
  props: {
    brewery: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      editbrewery: {
        breweryId: this.brewery.breweryId,
        breweryName: this.brewery.breweryName,
        brewerUsername: this.brewery.brewerUsername,
        contactInformation: this.brewery.contactInformation,
        streetAddress: this.brewery.streetAddress,
        city: this.brewery.city,
        stateCode: this.brewery.stateCode,
        postalCode: this.brewery.postalCode,
        logoFilename: this.brewery.logoFilename,
        breweryURL: this.brewery.breweryURL,
        menuURL: this.brewery.menuURL,
        ocbaInfoURL: this.brewery.ocbaInfoURL,
        mapURL: this.brewery.mapURL
      }
    };
  },
  methods: {
    submitForm() {
      if (!this.validateForm()) {
        return;
      }
      if (this.editbrewery.breweryId === 0) {
        // add
        breweryService
          .createBrewery(this.editbrewery)
          .then(response => {
            if (response.status === 201) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: 'A new brewery was added.',
                  type: 'success'
                }
              );
              this.$router.push({ name: 'brewery-list' });
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'creating');
          });
      } else {
        breweryService
          .updateBrewery(this.editbrewery)
          .then(response => {
            if (response.status === 200) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: ` brewery ${this.editbrewery.breweryId} was updated.`,
                  type: 'success'
                }
              );
              this.$router.push({ name: 'brewery-details', params: { breweryId: this.editbrewery.breweryId } });
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'updating');
          });
      }
    },
    handleFileChange(e) {
      const breweryLogoFile = e.target.files[0];
      const breweryLogoFilename = breweryLogoFile.name;
      this.editbrewery.logoFilename = breweryLogoFilename;
    },
    cancelForm() {
      if (this.editbrewery.breweryId === 0) {
        this.$router.push({ name: 'brewery-list' });
      } else {
        this.$router.push({ name: 'brewery-details', params: { breweryId: this.editbrewery.breweryId } });
      }
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
          "Error " + verb + " brewery. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " brewery. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " brewery. Request could not be created.");
      }
    },
    validateForm() {
      let msg = '';
      if (this.editbrewery.breweryName.length === 0) {
        msg += 'The brewery must have a name. ';
      }
      if (this.editbrewery.brewerUsername.length === 0) {
        msg += 'The brewery must have username set.';
      }
      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    }
  }
};
</script>

<style scoped>
.brewery-form-info {
  width: 50%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 10px;
  margin-top: 20px;
  margin-bottom: 20px;
}

.brewery-form-info label {
  white-space: nowrap;
  text-align: right;
}

.brewery-form-info input {
  width: 500px;
}

.brewery-form-info #stateCode {
  margin-right: 20px;
  width: 250px;
}

.brewery-form-info #postalCode {
  margin-left: 5px;
  width: 120px;
}

.brewery-form-data {
  width: 50%;
  display: grid;
  grid-template-columns: 1fr 3fr;
  gap: 10px;
  margin-bottom: 20px;
}

.brewery-form-data label {
  white-space: nowrap;
  text-align: right;
}

.brewery-form-data input {
  width: 1220px;
}

.brewery-form .actions {
  text-align: center;
  margin-top: 10px;
}

.btn-submit {
  margin-right: 20px;
  width: 100px;
}

.btn-cancel {
  width: 100px;
}

</style>