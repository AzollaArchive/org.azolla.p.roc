(function (root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module unless amdModuleId is set
    define('simditor-small', ["jquery", "simditor"], function (a0, b1) {
      return (root['SimditorSmall'] = factory(a0, b1));
    });
  } else if (typeof exports === 'object') {
    // Node. Does not work with strict CommonJS, but
    // only CommonJS-like environments that support module.exports,
    // like Node.
    module.exports = factory(require("jquery"), require("simditor"));
  } else {
    root['SimditorSmall'] = factory(jQuery, Simditor);
  }
}(this, function ($, Simditor) {

  var SimditorSmall,
    extend = function (child, parent) {
      for (var key in parent) {
        if (hasProp.call(parent, key)) child[key] = parent[key];
      }
      function ctor() {
        this.constructor = child;
      }

      ctor.prototype = parent.prototype;
      child.prototype = new ctor();
      child.__super__ = parent.prototype;
      return child;
    },
    hasProp = {}.hasOwnProperty;

  SimditorSmall = (function (superClass) {
    extend(SimditorSmall, superClass);

    function SimditorSmall() {
      return SimditorSmall.__super__.constructor.apply(this, arguments);
    }

    SimditorSmall.prototype.name = 'small';

    SimditorSmall.prototype.icon = 'small';//times,chain-broken,caret-down,caret-right,undo,font-minus

    SimditorSmall.prototype.htmlTag = 'small';

    SimditorSmall.prototype.disableTag = 'pre, code';

    SimditorSmall.prototype.command = function () {
      var $end, $start, range;
      range = this.editor.selection.getRange();
      if (this.active) {
        this.editor.selection.save();
        this.unsmall(range);
        this.editor.selection.restore();
        this.editor.trigger('valuechanged');
        return;
      }
      if (range.collapsed) {
        return;
      }
      this.editor.selection.save();
      $start = $(range.startContainer);
      $end = $(range.endContainer);
      if ($start.closest('small').length) {
        range.setStartBefore($start.closest('small')[0]);
      }
      if ($end.closest('small').length) {
        range.setEndAfter($end.closest('small')[0]);
      }
      this.small(range);
      this.editor.selection.restore();
      this.editor.trigger('valuechanged');
      if (this.editor.util.support.onselectionchange) {
        return this.editor.trigger('selectionchanged');
      }
    };

    SimditorSmall.prototype.small = function (range) {
      var $contents, $small;
      if (range == null) {
        range = this.editor.selection.range();
      }
      $contents = $(range.extractContents());
      $contents.find('small').each(function (index, ele) {
        return $(ele).replaceWith($(ele).html());
      });
      $small = $('<small>').append($contents);
      return range.insertNode($small[0]);
    };

    SimditorSmall.prototype.unsmall = function (range) {
      var $small;
      if (range == null) {
        range = this.editor.selection.range();
      }
      if (range.collapsed) {
        $small = $(range.commonAncestorContainer);
        if (!$small.is('small')) {
          $small = $small.parent();
        }
      } else if ($(range.startContainer).closest('small').length) {
        $small = $(range.startContainer).closest('small');
      } else if ($(range.endContainer).closest('small').length) {
        $small = $(range.endContainer).closest('small');
      }
      return $small.replaceWith($small.html());
    };

    return SimditorSmall;

  })(Simditor.Button);

  Simditor.Toolbar.addButton(SimditorSmall);

  return SimditorSmall;

}));
