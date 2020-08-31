import $ from 'jquery';
import 'popper.js';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

import CodeMirror from 'codemirror/lib/codemirror';
import 'codemirror/lib/codemirror.css';
import 'codemirror/mode/xml/xml';
import 'codemirror/theme/monokai.css'

import 'summernote/dist/summernote-bs4';
import 'summernote/dist/summernote-bs4.css';
import 'summernote/summernote-image-captionit.js';

$('.summernote').summernote({
  toolbar: [
      // [groupName, [list of button]]
      ['fontname', ['fontname']],
      ['fontsize', ['fontsize']],
      ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
      ['color', ['forecolor','color']],
      ['table', ['table']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['height', ['height']],
      ['insert',['picture','link','video']],
      ['view', ['fullscreen', 'help']]
    ],
  fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
  fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],

   popover: {
            image: [
                ['custom', ['captionIt']],
                ['imagesize', ['imageSize100', 'imageSize50', 'imageSize25']],
                ['float', ['floatLeft', 'floatRight', 'floatNone']],
                ['remove', ['removeMedia']]
            ],
        },
        captionIt:{
            figureClass:'{figure-class/es}',
            figcaptionClass:'{figcapture-class/es}',
            captionText:'{Default Caption Editable Placeholder Text if Title or Alt are empty}'
        }
});