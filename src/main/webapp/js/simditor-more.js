(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module unless amdModuleId is set
        define('simditor-more', ["jquery","simditor"], function (a0,b1) {
            return (root['SimditorMore'] = factory(a0,b1));
        });
    } else if (typeof exports === 'object') {
        // Node. Does not work with strict CommonJS, but
        // only CommonJS-like environments that support module.exports,
        // like Node.
        module.exports = factory(require("jquery"),require("simditor"));
    } else {
        root['SimditorMore'] = factory(jQuery,Simditor);
    }
}(this, function ($, Simditor) {

    var SimditorMore,
        extend = function(child, parent) { for (var key in parent) { if (hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; },
        hasProp = {}.hasOwnProperty;

    SimditorMore = (function(superClass) {
        extend(SimditorMore, superClass);

        function SimditorMore() {
            return SimditorMore.__super__.constructor.apply(this, arguments);
        }

        SimditorMore.prototype.name = 'more';

        SimditorMore.prototype.icon = 'more';

        SimditorMore.prototype.htmlTag = 'more';

        SimditorMore.prototype.status = function($node) {
            return true;
        };

        SimditorMore.prototype.command = function() {
            var $more, $newBlock, $nextBlock, $rootBlock;
            $rootBlock = this.editor.util.furthestBlockEl();
            $nextBlock = $rootBlock.next();
            if ($nextBlock.length > 0) {
                this.editor.selection.save();
            } else {
                $newBlock = $('<p/>').append(this.editor.util.phBr);
            }
            $more = $('<more/>').insertAfter($rootBlock);
            if ($newBlock) {
                $newBlock.insertAfter($more);
                this.editor.selection.setRangeAtStartOf($newBlock);
            } else {
                this.editor.selection.restore();
            }
            return this.editor.trigger('valuechanged');
        };

        return SimditorMore;

    })(Simditor.Button);

    Simditor.Toolbar.addButton(SimditorMore);

    return SimditorMore;

}));
