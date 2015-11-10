(function (root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module unless amdModuleId is set
    define('simditor-kbd', ["jquery", "simditor"], function (a0, b1) {
      return (root['SimditorKbd'] = factory(a0, b1));
    });
  } else if (typeof exports === 'object') {
    // Node. Does not work with strict CommonJS, but
    // only CommonJS-like environments that support module.exports,
    // like Node.
    module.exports = factory(require("jquery"), require("simditor"));
  } else {
    root['SimditorKbd'] = factory(jQuery, Simditor);
  }
}(this, function ($, Simditor) {

  var SimditorKbd,
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

  SimditorKbd = (function (superClass) {
    extend(SimditorKbd, superClass);

    function SimditorKbd() {
      return SimditorKbd.__super__.constructor.apply(this, arguments);
    }

    SimditorKbd.prototype.name = 'kbd';

    SimditorKbd.prototype.icon = 'kbd';//times,chain-broken,caret-down,caret-right,undo,font-minus

    SimditorKbd.prototype.htmlTag = 'kbd';

    SimditorKbd.prototype.disableTag = 'pre, code';

    SimditorKbd.prototype.command = function () {
      var $end, $start, range;
      range = this.editor.selection.getRange();
      if (this.active) {
        this.editor.selection.save();
        this.unkbd(range);
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
      if ($start.closest('kbd').length) {
        range.setStartBefore($start.closest('kbd')[0]);
      }
      if ($end.closest('kbd').length) {
        range.setEndAfter($end.closest('kbd')[0]);
      }
      this.kbd(range);
      this.editor.selection.restore();
      this.editor.trigger('valuechanged');
      if (this.editor.util.support.onselectionchange) {
        return this.editor.trigger('selectionchanged');
      }
    };

    SimditorKbd.prototype.kbd = function (range) {
      var $contents, $kbd;
      if (range == null) {
        range = this.editor.selection.range();
      }
      $contents = $(range.extractContents());
      $contents.find('kbd').each(function (index, ele) {
        return $(ele).replaceWith($(ele).html());
      });
      $kbd = $('<kbd>').append($contents);
      return range.insertNode($kbd[0]);
    };

    SimditorKbd.prototype.unkbd = function (range) {
      var $kbd;
      if (range == null) {
        range = this.editor.selection.range();
      }
      if (range.collapsed) {
        $kbd = $(range.commonAncestorContainer);
        if (!$kbd.is('kbd')) {
          $kbd = $kbd.parent();
        }
      } else if ($(range.startContainer).closest('kbd').length) {
        $kbd = $(range.startContainer).closest('kbd');
      } else if ($(range.endContainer).closest('kbd').length) {
        $kbd = $(range.endContainer).closest('kbd');
      }
      return $kbd.replaceWith($kbd.html());
    };

    return SimditorKbd;

  })(Simditor.Button);

  Simditor.Toolbar.addButton(SimditorKbd);

  return SimditorKbd;

}));
