// Functions
function closeFilter($filter) {
  var $toggle = $filter.find('.js-filter-toggle')
    , $selectedOption = $filter.find('.filter__option--is-selected').data('value')
  
  $filter.removeClass('filter--is-open')
  $toggle.html($selectedOption)
  $toggle.focus()
}

function openFilter($filter) {
  var $toggle = $filter.find('.js-filter-toggle')
    , $label = $filter.find('.js-filter-label').html()
    , $selectedOption = $filter.find('.filter__option--is-selected')

  $filter.addClass('filter--is-open')
  $toggle.html($label)
  $selectedOption.focus()
}

// Select all of the filters
var $filters = $('.js-filter')
  , $selects = $('.js-filter-select')

// Tell CSS that the JS has loaded
$filters.addClass('filter--is-ready')

// Disable tabbing through hidden select boxes
$selects.attr('tabindex', '-1')

$filters.each(function() {
  var $filter = $(this)
    , $label = $filter.children('.js-filter-label')
    , $control = $filter.children('.js-filter-control')
    , $select = $control.children('.js-filter-select')
    , $options = $select.children()
  
  // Create the custom select box element
  $control.append('<div class="filter__toggle js-filter-toggle" tabindex="0">' + $label.html() + '</div>')  

  // Create elements for each select box option
  $options.each(function(i, option) {
    if (option.value != '') {
      $control.append('<div class="filter__option js-filter-option" data-value="' + option.text + '" tabindex="0">' + option.text + '<div class="filter__clear js-filter-clear" tabindex="0"></div></div>')
    }
  })
})

// Toggle the visibility of the options
var $toggles = $('.js-filter-toggle')

function toggleFilter($this) {
  var $filter = $this.closest('.js-filter')

  if ($filters.hasClass('filter--is-open')) {
    closeFilter($filter)
  } else {
    openFilter($filter)
  }
}

$toggles.on('click', function() {
  toggleFilter($(this))
})

// Accessibility toggles
$(document).keyup(function(e) {
  var $filter = $('.filter--is-open')
    , $focusedElement = $(':focus')
    , $filterToggleFocused = $focusedElement.hasClass('js-filter-toggle')
    , $filterOptionFocused = $focusedElement.hasClass('js-filter-option')

  if (e.keyCode === 32) { // Spacebar
    if ($filterToggleFocused) {
      $filter = $focusedElement.closest('.js-filter')
      toggleFilter($filter)
    } else if ($filterOptionFocused) {
      selectOption($focusedElement)
    }
  }
  
  if ($filter.length && e.keyCode === 27) { // Escape
    closeFilter($filter)
  }
  
  if (e.keyCode === 38) { // Up key
    if (!$filter.length && $filterToggleFocused) {
      $filter = $focusedElement.closest('.js-filter')
      openFilter($filter)
    } else if ($filterToggleFocused || $filterOptionFocused) {
      $focusedElement.prev().focus()
    }
  }

  if (e.keyCode === 40) { // Down key
    if (!$filter.length && $filterToggleFocused) {
      $filter = $focusedElement.closest('.js-filter')
      openFilter($filter)
    } else if ($filterToggleFocused || $filterOptionFocused) {
      $focusedElement.next().focus()
    }
  }

})

// Close the dropdown when clicked off of
$(document).on('click', function(e) {
  if (!$(e.target).closest('.filter--is-open').length) {
    var $filter = $('.filter--is-open')
    closeFilter($filter)
  }
})

// Select an option
function selectOption($this) {
  var $filter = $this.closest('.js-filter')

  if (!$this.hasClass('filter__option--is-selected')) {
    var $oldSelectedOption = $filter.find('.filter__option')
      , $select = $filter.find('.js-filter-select')

    $oldSelectedOption.removeClass('filter__option--is-selected')
    $this.addClass('filter__option--is-selected')
    $filter.addClass('filter--has-value')
    $select.val($this.data('value'))
  }

  closeFilter($filter)
}

var $newOptions = $('.js-filter-option')

$newOptions.on('click', function() {
  selectOption($(this))
})

// Clear an option
var $clear = $('.js-filter-clear')

$clear.on('click', function(e) {
  e.stopPropagation()
  
  var $this = $(this)
    , $filter = $this.closest('.js-filter')
    , $selectedOption = $this.parent('.filter__option')
    , $select = $filter.find('.js-filter-select')
  
  $filter.removeClass('filter--has-value')
  $selectedOption.removeClass('filter__option--is-selected')
  $select.val('')

  closeFilter($filter)
})