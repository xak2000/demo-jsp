(function () {
  moment.locale('en_US'); // always en_US as provided by specification 

  // timePeriod rules
  var timePeriods = {
    LAST_MONTH: {
      startDate: function () { return moment().subtract(1, 'month').startOf('month'); },
      endDate: function () { return moment().subtract(1, 'month').endOf('month'); }
    },  
    LAST_QTR: {
      startDate: function () { return moment().subtract(1, 'quarter').startOf('quarter'); },
      endDate: function () { return moment().subtract(1, 'quarter').endOf('quarter');; }
    }, 
    LAST_YEAR: {
      startDate: function () { return moment().subtract(1, 'year').startOf('year'); },
      endDate: function () { return moment().subtract(1, 'year').endOf('year'); }
    }, 
    CURRENT_MONTH_TO_DATE: {
      startDate: function () { return moment().startOf('month'); }
    },  
    CURRENT_QTR_TO_DATE: {
      startDate: function () { return moment().startOf('quarter'); }
    }, 
    CURRENT_YEAR_TO_DATE: {
      startDate: function () { return moment().startOf('year'); }
    }, 
  };
  
  var startDateEl = document.getElementById('startDate');
  var endDateEl = document.getElementById('endDate');
  var timePeriodEl = document.getElementById('timePeriod');
  
  // to prevent sending 'timePeriod' field as query param
  timePeriodEl.removeAttribute("name");

  // set timePeriod field from current startDate/endDate fields
  // and after any change of these fields.
  setTimePeriodFrom(startDateEl.value, endDateEl.value);
  startDateEl.oninput = endDateEl.oninput = function (event) {
    setTimePeriodFrom(startDateEl.value, endDateEl.value);
  };
  $([startDateEl, endDateEl]).datepicker().on('changeDate', function(e) {
    setTimePeriodFrom(startDateEl.value, endDateEl.value);
  });

  // set startDate and/or endDate according to timePeriod rules
  // on every timePeriod change.
  timePeriodEl.onchange = function (event) {
    var timePeriod = timePeriods[timePeriodEl.value];
    if (timePeriod) {
      if (timePeriod.startDate) {
        startDateEl.value = formatDate(timePeriod.startDate());
        $(startDateEl).datepicker('update');
      }
      if (timePeriod.endDate) {
        endDateEl.value = formatDate(timePeriod.endDate());
        $(endDateEl).datepicker('update');
      } else {
        endDateEl.focus();
        endDateEl.select();
      }
    }
  };

  ///////////////
  // Functions //
  ///////////////

  /**
   * Set timePeriod field value from startDate and endDate string representations.
   * @param startDateStr startDate as string in US_MEDIUM format
   * @param endDateStr endDate as string in US_MEDIUM format
   */
  function setTimePeriodFrom(startDateStr, endDateStr) {
    var startDate = moment(startDateStr, 'MMM D, YYYY');
    var endDate = moment(endDateStr, 'MMM D, YYYY');
    var foundPeriod = '';
    
    for (var timePeriod in timePeriods) {
      var startDateEq = !timePeriods[timePeriod].startDate
          || startDate.isSame(timePeriods[timePeriod].startDate(), 'day'); 
      var endDateEq = !timePeriods[timePeriod].endDate
          || endDate.isSame(timePeriods[timePeriod].endDate(), 'day');
      if (startDateEq && endDateEq) { // found corresponding time period
        foundPeriod = timePeriod;
        break;
      }
    }
    
    timePeriodEl.value = foundPeriod;
  }

  /**
   * Formats moment or Date instance as string in US_MEDIUM format
   * @param date moment or Date instance
   * @returns string in US_MEDIUM format for given date
   */
  function formatDate(date) {
    return moment(date).format('ll'); // 'Mon D, YYYY' in en_US locale
  }

}());