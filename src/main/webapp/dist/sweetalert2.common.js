/*!
 * sweetalert2 v3.3.8
 * Released under the MIT License.
 */
'use strict';

var swalPrefix = 'swal2-';

var prefix = function(items) {
  var result = {};
  for (var i in items) {
    result[items[i]] = swalPrefix + items[i];
  }
  return result;
};

var swalClasses = prefix([
  'container',
  'modal',
  'overlay',
  'close',
  'content',
  'spacer',
  'confirm',
  'cancel',
  'icon',
  'image',
  'input',
  'select',
  'radio',
  'checkbox',
  'textarea',
  'validationerror'
]);

var iconTypes = prefix([
  'success',
  'warning',
  'info',
  'question',
  'error'
]);

var defaultParams = {
  title: '',
  text: '',
  html: '',
  type: null,
  animation: true,
  allowOutsideClick: true,
  allowEscapeKey: true,
  showConfirmButton: true,
  showCancelButton: false,
  preConfirm: null,
  confirmButtonText: 'OK',
  confirmButtonColor: '#3085d6',
  confirmButtonClass: null,
  cancelButtonText: 'Cancel',
  cancelButtonColor: '#aaa',
  cancelButtonClass: null,
  buttonsStyling: true,
  reverseButtons: false,
  showCloseButton: false,
  showLoaderOnConfirm: false,
  imageUrl: null,
  imageWidth: null,
  imageHeight: null,
  imageClass: null,
  timer: null,
  width: 500,
  padding: 20,
  background: '#fff',
  input: null, // 'text' | 'email' | 'password' | 'select' | 'radio' | 'checkbox' | 'textarea' | 'file'
  inputPlaceholder: '',
  inputValue: '',
  inputOptions: {},
  inputAutoTrim: true,
  inputClass: null,
  inputAttributes: {},
  inputValidator: null
};

var sweetHTML = '<div class="' + swalClasses.overlay + '" tabIndex="-1"></div>' +
  '<div class="' + swalClasses.modal + '" style="display: none" tabIndex="-1">' +
    '<div class="' + swalClasses.icon + ' ' + iconTypes.error + '">' +
      '<span class="x-mark"><span class="line left"></span><span class="line right"></span></span>' +
    '</div>' +
    '<div class="' + swalClasses.icon + ' ' + iconTypes.question + '">?</div>' +
    '<div class="' + swalClasses.icon + ' ' + iconTypes.warning + '">!</div>' +
    '<div class="' + swalClasses.icon + ' ' + iconTypes.info + '">i</div>' +
    '<div class="' + swalClasses.icon + ' ' + iconTypes.success + '">' +
      '<span class="line tip"></span> <span class="line long"></span>' +
      '<div class="placeholder"></div> <div class="fix"></div>' +
    '</div>' +
    '<img class="' + swalClasses.image + '">' +
    '<h2></h2>' +
    '<div class="' + swalClasses.content + '"></div>' +
    '<input class="' + swalClasses.input + '">' +
    '<select class="' + swalClasses.select + '"></select>' +
    '<div class="' + swalClasses.radio + '"></div>' +
    '<label for="' + swalClasses.checkbox + '" class="' + swalClasses.checkbox + '">' +
      '<input type="checkbox" id="' + swalClasses.checkbox + '">' +
    '</label>' +
    '<textarea class="' + swalClasses.textarea + '"></textarea>' +
    '<div class="' + swalClasses.validationerror + '"></div>' +
    '<hr class="' + swalClasses.spacer + '">' +
    '<button class="' + swalClasses.confirm + '">OK</button>' +
    '<button class="' + swalClasses.cancel + '">Cancel</button>' +
    '<span class="' + swalClasses.close + '">&times;</span>' +
  '</div>';

var extend = function(a, b) {
  for (var key in b) {
    if (b.hasOwnProperty(key)) {
      a[key] = b[key];
    }
  }

  return a;
};


/*
 * Set hover, active and focus-states for buttons (source: http://www.sitepoint.com/javascript-generate-lighter-darker-color)
 */
var colorLuminance = function(hex, lum) {
  // Validate hex string
  hex = String(hex).replace(/[^0-9a-f]/gi, '');
  if (hex.length < 6) {
    hex = hex[0] + hex[0] + hex[1] + hex[1] + hex[2] + hex[2];
  }
  lum = lum || 0;

  // Convert to decimal and change luminosity
  var rgb = '#';
  for (var i = 0; i < 3; i++) {
    var c = parseInt(hex.substr(i * 2, 2), 16);
    c = Math.round(Math.min(Math.max(0, c + (c * lum)), 255)).toString(16);
    rgb += ('00' + c).substr(c.length);
  }

  return rgb;
};

var mediaqueryId = swalPrefix + 'mediaquery';

// Remember state in cases where opening and handling a modal will fiddle with it.
var states = {
    previousDocumentClick: null,
    previousWindowKeyDown: null,
    previousActiveElement: null
};

/*
 * Manipulate DOM
 */
var elementByClass = function(className) {
  return document.querySelector('.' + className);
};

var getModal = function() {
  return elementByClass(swalClasses.modal);
};

var getOverlay = function() {
  return elementByClass(swalClasses.overlay);
};

var getConfirmButton = function() {
  return elementByClass(swalClasses.confirm);
};

var getCancelButton = function() {
  return elementByClass(swalClasses.cancel);
};

var hasClass = function(elem, className) {
  return new RegExp(' ' + className + ' ').test(' ' + elem.className + ' ');
};

var focusInput = function(input) {
  input.focus();

  // http://stackoverflow.com/a/2345915/1331425
  var val = input.value;
  input.value = '';
  input.value = val;
};

var addClass = function(elem, className) {
  if (className && !hasClass(elem, className)) {
    elem.className += ' ' + className;
  }
};

var removeClass = function(elem, className) {
  var newClass = ' ' + elem.className.replace(/[\t\r\n]/g, ' ') + ' ';
  if (hasClass(elem, className)) {
    while (newClass.indexOf(' ' + className + ' ') >= 0) {
      newClass = newClass.replace(' ' + className + ' ', ' ');
    }
    elem.className = newClass.replace(/^\s+|\s+$/g, '');
  }
};

var getChildByClass = function(elem, className) {
  for (var i = 0; i < elem.childNodes.length; i++) {
    if (elem.childNodes[i].classList.contains(className)) {
      return elem.childNodes[i];
    }
  }
};

var _show = function(elem) {
  elem.style.opacity = '';
  elem.style.display = 'block';
};

var show = function(elems) {
  if (elems && !elems.length) {
    return _show(elems);
  }
  for (var i = 0; i < elems.length; ++i) {
    _show(elems[i]);
  }
};

var _hide = function(elem) {
  elem.style.opacity = '';
  elem.style.display = 'none';
};

var hide = function(elems) {
  if (elems && !elems.length) {
    return _hide(elems);
  }
  for (var i = 0; i < elems.length; ++i) {
    _hide(elems[i]);
  }
};

var removeStyleProperty = function(elem, property) {
  if (elem.style.removeProperty) {
    elem.style.removeProperty(property);
  } else {
    elem.style.removeAttribute(property);
  }
};

var getTopMargin = function(elem) {
  elem.style.left = '-9999px';
  elem.style.display = 'block';

  var height = elem.clientHeight;
  var paddingTop = parseInt(getComputedStyle(elem).getPropertyValue('padding-top'), 10);

  elem.style.left = '';
  elem.style.display = 'none';
  return ('-' + parseInt(height / 2 + paddingTop, 10) + 'px');
};

var fadeIn = function(elem, interval) {
  if (+elem.style.opacity < 1) {
    interval = interval || 16;
    elem.style.opacity = 0;
    elem.style.display = 'block';
    var last = +new Date();
    var tick = function() {
      var newOpacity = +elem.style.opacity + (new Date() - last) / 100;
      elem.style.opacity = (newOpacity > 1) ? 1 : newOpacity;
      last = +new Date();

      if (+elem.style.opacity < 1) {
        setTimeout(tick, interval);
      }
    };
    tick();
  }
};

var fadeOut = function(elem, interval) {
  if (+elem.style.opacity > 0) {
    interval = interval || 16;
    var opacity = elem.style.opacity;
    var last = +new Date();
    var tick = function() {
      var change = new Date() - last;
      var newOpacity = +elem.style.opacity - change / (opacity * 100);
      elem.style.opacity = newOpacity;
      last = +new Date();

      if (+elem.style.opacity > 0) {
        setTimeout(tick, interval);
      } else {
        _hide(elem);
      }
    };
    tick();
  }
};

var stopEventPropagation = function(e) {
  // In particular, make sure the space bar doesn't scroll the main window.
  if (typeof e.stopPropagation === 'function') {
    e.stopPropagation();
    e.preventDefault();
  } else if (window.event && window.event.hasOwnProperty('cancelBubble')) {
    window.event.cancelBubble = true;
  }
};

var animationEndEvent = (function() {
  var testEl = document.createElement('div'),
    transEndEventNames = {
      'WebkitAnimation': 'webkitAnimationEnd',
      'MozAnimation': 'animationend',
      'OAnimation': 'oAnimationEnd oanimationend',
      'msAnimation': 'MSAnimationEnd',
      'animation': 'animationend'
    };
  for (var i in transEndEventNames) {
    if (transEndEventNames.hasOwnProperty(i) &&
      testEl.style[i] !== undefined) {
      return transEndEventNames[i];
    }
  }

  return false;
})();


// Reset the page to its previous state
var resetPrevState = function() {
  var modal = getModal();
  window.onkeydown = states.previousWindowKeyDown;
  document.onclick = states.previousDocumentClick;
  if (states.previousActiveElement) {
    states.previousActiveElement.focus();
  }
  clearTimeout(modal.timeout);

  // Remove dynamically created media query
  var head = document.getElementsByTagName('head')[0];
  var mediaquery = document.getElementById(mediaqueryId);
  if (mediaquery) {
    head.removeChild(mediaquery);
  }
};

var modalParams = extend({}, defaultParams);

/*
 * Set type, text and actions on modal
 */
var setParameters = function(params) {
  var i;
  var modal = getModal();

  // set modal width, padding and margin-left
  modal.style.width = params.width + 'px';
  modal.style.padding = params.padding + 'px';
  modal.style.marginLeft = -params.width / 2 + 'px';
  modal.style.background = params.background;

  // add dynamic media query css
  var head = document.getElementsByTagName('head')[0];
  var cssNode = document.createElement('style');
  cssNode.type = 'text/css';
  cssNode.id = mediaqueryId;
  var margin = 5; // %
  var mediaQueryMaxWidth = params.width + parseInt(params.width * (margin/100) * 2, 10);
  cssNode.innerHTML =
    '@media screen and (max-width: ' + mediaQueryMaxWidth + 'px) {' +
      '.' + swalClasses.modal + ' {' +
        'width: auto !important;' +
        'left: ' + margin + '% !important;' +
        'right: ' + margin + '% !important;' +
        'margin-left: 0 !important;' +
      '}' +
    '}';
  head.appendChild(cssNode);

  var $title = modal.querySelector('h2');
  var $content = modal.querySelector('.' + swalClasses.content);
  var $confirmBtn = getConfirmButton();
  var $cancelBtn = getCancelButton();
  var $spacer = modal.querySelector('.' + swalClasses.spacer);
  var $closeButton = modal.querySelector('.' + swalClasses.close);

  // Title
  $title.innerHTML = params.title.split('\n').join('<br>');

  // Content
  if (params.text || params.html) {
    if (typeof params.html === 'object') {
      $content.innerHTML = '';
      if (0 in params.html) {
        for (i = 0; i in params.html; i++) {
          $content.appendChild(params.html[i]);
        }
      } else {
        $content.appendChild(params.html);
      }
    } else {
      $content.innerHTML = params.html || (params.text.split('\n').join('<br>'));
    }
    show($content);
  } else {
    hide($content);
  }

  // Close button
  if (params.showCloseButton) {
    show($closeButton);
  } else {
    hide($closeButton);
  }

  // Custom Class
  modal.className = swalClasses.modal;
  if (params.customClass) {
    addClass(modal, params.customClass);
  }

  // Icon
  hide(modal.querySelectorAll('.' + swalClasses.icon));
  if (params.type) {
    var validType = false;
    for (var iconType in iconTypes) {
      if (params.type === iconType) {
        validType = true;
        break;
      }
    }
    if (!validType) {
      console.error('Unknown alert type: ' + params.type);
      return false;
    }
    var $icon = modal.querySelector('.' + swalClasses.icon + '.' + iconTypes[params.type]);
    show($icon);

    // Animate icon
    switch (params.type) {
      case 'success':
        addClass($icon, 'animate');
        addClass($icon.querySelector('.tip'), 'animate-success-tip');
        addClass($icon.querySelector('.long'), 'animate-success-long');
        break;
      case 'error':
        addClass($icon, 'animate-error-icon');
        addClass($icon.querySelector('.x-mark'), 'animate-x-mark');
        break;
      case 'warning':
        addClass($icon, 'pulse-warning');
        break;
      default:
        break;
    }

  }

  // Custom image
  var $customImage = modal.querySelector('.' + swalClasses.image);
  if (params.imageUrl) {
    $customImage.setAttribute('src', params.imageUrl);
    show($customImage);

    if (params.imageWidth) {
      $customImage.setAttribute('width', params.imageWidth);
    }

    if (params.imageHeight) {
      $customImage.setAttribute('height', params.imageHeight);
    }

    if (params.imageClass) {
      addClass($customImage, params.imageClass);
    }
  } else {
    hide($customImage);
  }

  // Cancel button
  if (params.showCancelButton) {
    $cancelBtn.style.display = 'inline-block';
  } else {
    hide($cancelBtn);
  }

  // Confirm button
  if (params.showConfirmButton) {
    removeStyleProperty($confirmBtn, 'display');
  } else {
    hide($confirmBtn);
  }

  // Buttons spacer
  if (!params.showConfirmButton && !params.showCancelButton) {
    hide($spacer);
  } else {
    show($spacer);
  }

  // Edit text on cancel and confirm buttons
  $confirmBtn.innerHTML = params.confirmButtonText;
  $cancelBtn.innerHTML = params.cancelButtonText;

  // Set buttons to selected background colors
  if (params.buttonsStyling) {
    $confirmBtn.style.backgroundColor = params.confirmButtonColor;
    $cancelBtn.style.backgroundColor = params.cancelButtonColor;
  }

  // Add buttons custom classes
  $confirmBtn.className = swalClasses.confirm;
  addClass($confirmBtn, params.confirmButtonClass);
  $cancelBtn.className = swalClasses.cancel;
  addClass($cancelBtn, params.cancelButtonClass);

  // Buttons styling
  if (params.buttonsStyling) {
    addClass($confirmBtn, 'styled');
    addClass($cancelBtn, 'styled');
  } else {
    removeClass($confirmBtn, 'styled');
    removeClass($cancelBtn, 'styled');

    $confirmBtn.style.backgroundColor = $confirmBtn.style.borderLeftColor = $confirmBtn.style.borderRightColor = '';
    $cancelBtn.style.backgroundColor = $cancelBtn.style.borderLeftColor = $cancelBtn.style.borderRightColor = '';
  }

  // CSS animation
  if (params.animation === true) {
    removeClass(modal, 'no-animation');
  } else {
    addClass(modal, 'no-animation');
  }
};

/*
 * Animations
 */
var openModal = function(animation) {
  var modal = getModal();
  if (animation) {
    fadeIn(getOverlay(), 10);
    addClass(modal, 'show-swal2');
    removeClass(modal, 'hide-swal2');
  } else {
    show(getOverlay());
  }
  show(modal);

  states.previousActiveElement = document.activeElement;

  addClass(modal, 'visible');
};

/*
 * Set 'margin-top'-property on modal based on its computed height
 */
var fixVerticalPosition = function() {
  var modal = getModal();

  modal.style.marginTop = getTopMargin(modal);
};

function modalDependant() {

  if (arguments[0] === undefined) {
    console.error('sweetAlert2 expects at least 1 attribute!');
    return false;
  }

  var params = extend({}, modalParams);

  switch (typeof arguments[0]) {

    case 'string':
      params.title = arguments[0];
      params.text  = arguments[1] || '';
      params.type  = arguments[2] || '';

      break;

    case 'object':
      extend(params, arguments[0]);
      params.extraParams = arguments[0].extraParams;

      if (params.input === 'email' && params.inputValidator === null) {
        params.inputValidator = function(email) {
          return new Promise(function(resolve, reject) {
            var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            if (emailRegex.test(email)) {
              resolve();
            } else {
              reject('Invalid email address');
            }
          });
        };
      }

      break;

    default:
      console.error('Unexpected type of argument! Expected "string" or "object", got ' + typeof arguments[0]);
      return false;
  }

  setParameters(params);
  fixVerticalPosition();
  openModal(params.animation);

  // Modal interactions
  var modal = getModal();

  return new Promise(function(resolve) {
    // Close on timer
    if (params.timer) {
      modal.timeout = setTimeout(function() {
        sweetAlert.closeModal();
        resolve(undefined);
      }, params.timer);
    }

    // input/select autofocus
    var getInput = function() {
      switch (params.input) {
        case 'select':
          return getChildByClass(modal, swalClasses.select);
        case 'radio':
          return modal.querySelector('.' + swalClasses.radio + ' input:checked') ||
            modal.querySelector('.' + swalClasses.radio + ' input:first-child');
        case 'checkbox':
          return modal.querySelector('#' + swalClasses.checkbox);
        case 'textarea':
          return getChildByClass(modal, swalClasses.textarea);
        default:
          return getChildByClass(modal, swalClasses.input);
      }
    };
    var getInputValue = function() {
      var input = getInput();
      switch (params.input) {
        case 'checkbox':
          return input.checked ? 1 : 0;
        case 'radio':
          return input.checked ? input.value : null;
        case 'file':
          return input.files.length ? input.files[0] : null;
        default:
          return params.inputAutoTrim? input.value.trim() : input.value;
      }
    };

    if (params.input) {
      setTimeout(function() {
        var input = getInput();
        if (input) {
          focusInput(input);
        }
      }, 0);
    }

    var confirm = function(value) {
      if (params.showLoaderOnConfirm) {
        sweetAlert.showLoading();
      }

      if (params.preConfirm) {
        params.preConfirm(value, params.extraParams).then(
          function(preConfirmValue) {
            resolve(preConfirmValue || value);
            sweetAlert.closeModal();
          },
          function() {
            sweetAlert.hideLoading();
          }
        );
      } else {
        resolve(value);
        sweetAlert.closeModal();
      }
    };

    // Mouse interactions
    var onButtonEvent = function(event) {
      var e = event || window.event;
      var target = e.target || e.srcElement;
      var targetedConfirm = hasClass(target, swalClasses.confirm);
      var targetedCancel  = hasClass(target, swalClasses.cancel);
      var modalIsVisible  = hasClass(modal, 'visible');

      switch (e.type) {
        case 'mouseover':
        case 'mouseup':
        case 'focus':
          if (params.buttonsStyling) {
            if (targetedConfirm) {
              target.style.backgroundColor = colorLuminance(params.confirmButtonColor, -0.1);
            } else if (targetedCancel) {
              target.style.backgroundColor = colorLuminance(params.cancelButtonColor, -0.1);
            }
          }
          break;
        case 'mouseout':
        case 'blur':
          if (params.buttonsStyling) {
            if (targetedConfirm) {
              target.style.backgroundColor = params.confirmButtonColor;
            } else if (targetedCancel) {
              target.style.backgroundColor = params.cancelButtonColor;
            }
          }
          break;
        case 'mousedown':
          if (params.buttonsStyling) {
            if (targetedConfirm) {
              target.style.backgroundColor = colorLuminance(params.confirmButtonColor, -0.2);
            } else if (targetedCancel) {
              target.style.backgroundColor = colorLuminance(params.cancelButtonColor, -0.2);
            }
          }
          break;
        case 'click':
          // Clicked 'confirm'
          if (targetedConfirm && modalIsVisible) {
            if (params.input) {
              var inputValue = getInputValue();

              if (params.inputValidator) {
                sweetAlert.disableInput();
                params.inputValidator(inputValue, params.extraParams).then(
                  function() {
                    sweetAlert.enableInput();
                    confirm(inputValue);
                  },
                  function(error) {
                    sweetAlert.enableInput();
                    sweetAlert.showValidationError(error);
                  }
                );
              } else {
                confirm(inputValue);
              }

            } else {
              confirm(true);
            }

          // Clicked 'cancel'
          } else if (targetedCancel && modalIsVisible) {

            sweetAlert.closeModal();
            resolve(false);
          }

          break;
        default:
      }
    };

    var $buttons = modal.querySelectorAll('button');
    var i;
    for (i = 0; i < $buttons.length; i++) {
      $buttons[i].onclick     = onButtonEvent;
      $buttons[i].onmouseover = onButtonEvent;
      $buttons[i].onmouseout  = onButtonEvent;
      $buttons[i].onmousedown = onButtonEvent;
    }

    // Remember the current document.onclick event.
    states.previousDocumentClick = document.onclick;
    document.onclick = function(event) {
      var e = event || window.event;
      var target = e.target || e.srcElement;

      if (hasClass(target, swalClasses.close) || (target === getOverlay() && params.allowOutsideClick)) {
        sweetAlert.closeModal();
        resolve(undefined);
      }
    };

    // Keyboard interactions
    var $confirmButton = getConfirmButton();
    var $cancelButton = getCancelButton();
    var $modalElements = [$confirmButton, $cancelButton].concat(Array.prototype.slice.call(
      modal.querySelectorAll('button:not([class^=' + swalPrefix + ']), input:not([type=hidden]), textarea, select')
    ));
    for (i = 0; i < $modalElements.length; i++) {
      $modalElements[i].onfocus = onButtonEvent;
      $modalElements[i].onblur = onButtonEvent;
    }

    // Reverse buttons if neede d
    if (params.reverseButtons) {
      $confirmButton.parentNode.insertBefore($cancelButton, $confirmButton);
    }

    function setFocus(index, increment) {
      // search for visible elements and select the next possible match
      for (var i = 0; i < $modalElements.length; i++) {
        index = index + increment;

        // rollover to first item
        if (index === $modalElements.length) {
          index = 0;

        // go to last item
        } else if (index === -1) {
          index = $modalElements.length - 1;
        }

        // determine if element is visible, the following is borrowed from jqeury $(elem).is(':visible') implementation
        if (
          $modalElements[index].offsetWidth ||
          $modalElements[index].offsetHeight ||
          $modalElements[index].getClientRects().length
        ) {
          $modalElements[index].focus();
          return;
        }
      }
    }

    // Focus the first element (input or button)
    setFocus(-1, 1);

    function handleKeyDown(event) {
      var e = event || window.event;
      var keyCode = e.keyCode || e.which;

      if ([9, 13, 32, 27].indexOf(keyCode) === -1) {
        // Don't do work on keys we don't care about.
        return;
      }

      var $targetElement = e.target || e.srcElement;

      var btnIndex = -1; // Find the button - note, this is a nodelist, not an array.
      for (var i = 0; i < $modalElements.length; i++) {
        if ($targetElement === $modalElements[i]) {
          btnIndex = i;
          break;
        }
      }

      // TAB
      if (keyCode === 9) {
        if (!e.shiftKey) {
          // Cycle to the next button
          setFocus(btnIndex, 1);
        } else {
          // Cycle to the prev button
          setFocus(btnIndex, -1);
        }

        stopEventPropagation(e);

      } else {
        if (keyCode === 13 || keyCode === 32) {
          if (btnIndex === -1) {
            // ENTER/SPACE clicked outside of a button.
            fireClick($confirmButton, e);
          }
        } else if (keyCode === 27 && params.allowEscapeKey === true) {
          sweetAlert.closeModal();
          resolve(undefined);
        }
      }
    }

    states.previousWindowKeyDown = window.onkeydown;
    window.onkeydown = handleKeyDown;

    // Loading state
    if (params.buttonsStyling) {
      $confirmButton.style.borderLeftColor = params.confirmButtonColor;
      $confirmButton.style.borderRightColor = params.confirmButtonColor;
    }

    /**
     * Show spinner instead of Confirm button and disable Cancel button
     */
    sweetAlert.showLoading = sweetAlert.enableLoading = function() {
      addClass($confirmButton, 'loading');
      addClass(modal, 'loading');
      $cancelButton.disabled = true;
    };

    /**
     * Show spinner instead of Confirm button and disable Cancel button
     */
    sweetAlert.hideLoading = sweetAlert.disableLoading = function() {
      removeClass($confirmButton, 'loading');
      removeClass(modal, 'loading');
      $cancelButton.disabled = false;
    };

    sweetAlert.enableButtons = function() {
      $confirmButton.disabled = false;
      $cancelButton.disabled = false;
    };

    sweetAlert.disableButtons = function() {
      $confirmButton.disabled = true;
      $cancelButton.disabled = true;
    };

    sweetAlert.enableInput = function() {
      var input = getInput();
      if (input.type === 'radio') {
        var radiosContainer = input.parentNode.parentNode;
        var radios = radiosContainer.querySelectorAll('input');
        for (var i = 0; i < radios.length; i++) {
          radios[i].disabled = false;
        }
      } else {
        input.disabled = false;
      }
    };

    sweetAlert.disableInput = function() {
      var input = getInput();
      if (input.type === 'radio') {
        var radiosContainer = input.parentNode.parentNode;
        var radios = radiosContainer.querySelectorAll('input');
        for (var i = 0; i < radios.length; i++) {
          radios[i].disabled = true;
        }
      } else {
        input.disabled = true;
      }
    };

    sweetAlert.showValidationError = function(error) {
      var $validationError = modal.querySelector('.' + swalClasses.validationerror);
      $validationError.innerHTML = error;
      show($validationError);

      var input = getInput();
      focusInput(input);
      addClass(input, 'error');
    };

    sweetAlert.resetValidationError = function() {
      var $validationError = modal.querySelector('.' + swalClasses.validationerror);
      hide($validationError);

      var input = getInput();
      if (input) {
        removeClass(input, 'error');
      }
    };

    sweetAlert.enableButtons();
    sweetAlert.hideLoading();
    sweetAlert.resetValidationError();

    // input, select
    var inputTypes = ['input', 'select', 'radio', 'checkbox', 'textarea'];
    var input;
    for (i = 0; i < inputTypes.length; i++) {
      var inputClass = swalClasses[inputTypes[i]];
      input = getChildByClass(modal, inputClass);

      // set attributes
      while (input.attributes.length > 0) {
        input.removeAttribute(input.attributes[0].name);
      }
      for (var attr in params.inputAttributes) {
        input.setAttribute(attr, params.inputAttributes[attr]);
      }

      // set class
      input.className = inputClass;
      if (params.inputClass) {
        addClass(input, params.inputClass);
      }

      _hide(input);
    }

    var populateInputOptions;
    switch (params.input) {
      case 'text':
      case 'email':
      case 'password':
      case 'file':
        input = getChildByClass(modal, swalClasses.input);
        input.value = params.inputValue;
        input.placeholder = params.inputPlaceholder;
        input.type = params.input;
        _show(input);
        break;
      case 'select':
        var select = getChildByClass(modal, swalClasses.select);
        select.innerHTML = '';
        if (params.inputPlaceholder) {
          var placeholder = document.createElement('option');
          placeholder.innerHTML = params.inputPlaceholder;
          placeholder.value = '';
          placeholder.disabled = true;
          placeholder.selected = true;
          select.appendChild(placeholder);
        }
        populateInputOptions = function(inputOptions) {
          for (var optionValue in inputOptions) {
            var option = document.createElement('option');
            option.value = optionValue;
            option.innerHTML = inputOptions[optionValue];
            if (params.inputValue === optionValue) {
              option.selected = true;
            }
            select.appendChild(option);
          }
          _show(select);
          select.focus();
        };
        break;
      case 'radio':
        var radio = getChildByClass(modal, swalClasses.radio);
        radio.innerHTML = '';
        populateInputOptions = function(inputOptions) {
          for (var radioValue in inputOptions) {
            var id = 1;
            var radioInput = document.createElement('input');
            var radioLabel = document.createElement('label');
            var radioLabelSpan = document.createElement('span');
            radioInput.type = 'radio';
            radioInput.name = swalClasses.radio;
            radioInput.value = radioValue;
            radioInput.id = swalClasses.radio + '-' + (id++);
            if (params.inputValue === radioValue) {
              radioInput.checked = true;
            }
            radioLabelSpan.innerHTML = inputOptions[radioValue];
            radioLabel.appendChild(radioInput);
            radioLabel.appendChild(radioLabelSpan);
            radioLabel.for = radioInput.id;
            radio.appendChild(radioLabel);
          }
          _show(radio);
          var radios = radio.querySelectorAll('input');
          if (radios.length) {
            radios[0].focus();
          }
        };
        break;
      case 'checkbox':
        var checkbox = getChildByClass(modal, swalClasses.checkbox);
        var checkboxInput = modal.querySelector('#' + swalClasses.checkbox);
        checkboxInput.value = 1;
        checkboxInput.checked = Boolean(params.inputValue);
        var label = checkbox.getElementsByTagName('span');
        if (label.length) {
          checkbox.removeChild(label[0]);
        }
        label = document.createElement('span');
        label.innerHTML = params.inputPlaceholder;
        checkbox.appendChild(label);
        _show(checkbox);
        break;
      case 'textarea':
        var textarea = getChildByClass(modal, swalClasses.textarea);
        textarea.value = params.inputValue;
        textarea.placeholder = params.inputPlaceholder;
        _show(textarea);
        break;
      case null:
        break;
      default:
        console.error('Unexpected type of input! Expected "text" or "email" or "password", "select", "checkbox", "textarea" or "file", got ' + typeof arguments[0]);
        break;
    }

    if (params.input === 'select' || params.input === 'radio') {
      if (params.inputOptions instanceof Promise) {
        sweetAlert.showLoading();
        params.inputOptions.then(function(inputOptions) {
          sweetAlert.hideLoading();
          populateInputOptions(inputOptions);
        });
      } else if (typeof params.inputOptions === 'object') {
        populateInputOptions(params.inputOptions);
      } else {
        console.error('Unexpected type of inputOptions! Expected object or Promise, got ' + params.inputOptions);
      }
    }
  });
}

// SweetAlert function
function sweetAlert() {
  // Copy arguments to the local args variable
  var args = arguments;
  var modal = getModal();

  if (modal === null) {
    sweetAlert.init();
    modal = getModal();
  }

  if (hasClass(modal, 'visible')) {
    resetPrevState();
  }

  return modalDependant.apply(this, args);
}

/*
 * Global function for chaining sweetAlert modals
 */
sweetAlert.queue = function(steps) {
  return new Promise(function(resolve, reject) {
    (function step(i, callback) {
      if (i < steps.length) {
        sweetAlert(steps[i]).then(function(isConfirm) {
          if (isConfirm) {
            step(i+1, callback);
          } else {
            reject();
          }
        });
      } else {
        resolve();
      }
    })(0);
  });
};

/*
 * Global function to close sweetAlert
 */
sweetAlert.close = sweetAlert.closeModal = function() {
  var modal = getModal();
  removeClass(modal, 'show-swal2');
  addClass(modal, 'hide-swal2');
  removeClass(modal, 'visible');

  // Reset icon animations
  var $successIcon = modal.querySelector('.' + swalClasses.icon + '.' + iconTypes.success);
  removeClass($successIcon, 'animate');
  removeClass($successIcon.querySelector('.tip'), 'animate-success-tip');
  removeClass($successIcon.querySelector('.long'), 'animate-success-long');

  var $errorIcon = modal.querySelector('.' + swalClasses.icon + '.' + iconTypes.error);
  removeClass($errorIcon, 'animate-error-icon');
  removeClass($errorIcon.querySelector('.x-mark'), 'animate-x-mark');

  var $warningIcon = modal.querySelector('.' + swalClasses.icon + '.' + iconTypes.warning);
  removeClass($warningIcon, 'pulse-warning');

  resetPrevState();

  if (animationEndEvent && !hasClass(modal, 'no-animation')) {
    modal.addEventListener(animationEndEvent, function swalCloseEventFinished() {
      modal.removeEventListener(animationEndEvent, swalCloseEventFinished);
      if (hasClass(modal, 'hide-swal2')) {
        _hide(modal);
        fadeOut(getOverlay(), 0);
      }
    });
  } else {
    _hide(modal);
    _hide(getOverlay());
  }
};

/*
 * Global function to click 'Confirm' button
 */
sweetAlert.clickConfirm = function() {
  getConfirmButton().click();
};

/*
 * Global function to click 'Cancel' button
 */
sweetAlert.clickCancel = function() {
  getCancelButton().click();
};

/*
 * Add modal + overlay to DOM
 */
sweetAlert.init = function() {
  if (typeof document === 'undefined') {
    console.log('SweetAlert2 requires document to initialize');
    return;
  } else if (document.getElementsByClassName(swalClasses.container).length) {
    return;
  }

  var sweetWrap = document.createElement('div');
  sweetWrap.className = swalClasses.container;

  sweetWrap.innerHTML = sweetHTML;

  document.body.appendChild(sweetWrap);

  var modal = getModal();
  var $input = getChildByClass(modal, swalClasses.input);
  var $select = getChildByClass(modal, swalClasses.select);
  var $checkbox = modal.querySelector('#' + swalClasses.checkbox);
  var $textarea = getChildByClass(modal, swalClasses.textarea);

  $input.oninput = function() {
    sweetAlert.resetValidationError();
  };

  $input.onkeyup = function(event) {
    event.stopPropagation();
    if (event.keyCode === 13) {
      sweetAlert.clickConfirm();
    }
  };

  $select.onchange = function() {
    sweetAlert.resetValidationError();
  };

  $checkbox.onchange = function() {
    sweetAlert.resetValidationError();
  };

  $textarea.onchange = function() {
    sweetAlert.resetValidationError();
  };
};

/**
 * Set default params for each popup
 * @param {Object} userParams
 */
sweetAlert.setDefaults = function(userParams) {
  if (!userParams) {
    throw new Error('userParams is required');
  }
  if (typeof userParams !== 'object') {
    throw new Error('userParams has to be a object');
  }

  extend(modalParams, userParams);
};

/**
 * Reset default params for each popup
 */
sweetAlert.resetDefaults = function() {
  modalParams = extend({}, defaultParams);
};

sweetAlert.version = '3.3.8';

window.sweetAlert = window.swal = sweetAlert;

/*
* If library is injected after page has loaded
*/
(function() {
  if (document.readyState === 'complete' || document.readyState === 'interactive' && document.body) {
    sweetAlert.init();
  } else {
    document.addEventListener('DOMContentLoaded', function onDomContentLoaded() {
      document.removeEventListener('DOMContentLoaded', onDomContentLoaded, false);
      sweetAlert.init();
    }, false);
  }
})();

module.exports = sweetAlert;