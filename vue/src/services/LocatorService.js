// LocatorService.js

import axios from 'axios';

export default{
  getCoffee(locationId){
    let queryURL = "https://api.yelp.com/v3/businesses/search?location="+locationId+"&term=coffee+tea&radius=20000&sort_by=distance&limit=24";
    const apiKey = "_VJj6fsEV44UAy-Cr65nlJl3GVdKOgK-vjlwwC7Nd6mxMJdt3rVRPGrJ8ZerjT-M7NseO0eyVDRSl2ZrOn2Ck6A8BZmdOUX0svKN-3cQfKt_dtgBaPpLk-RNfoGiZnYx"; 
    
    return axios.get(queryURL, { 
      headers: {
        Authorization: `Bearer ${apiKey}`
      }
    })
  }
};