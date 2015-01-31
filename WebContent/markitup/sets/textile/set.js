// -------------------------------------------------------------------
// markItUp!
// -------------------------------------------------------------------
// Copyright (C) 2008 Jay Salvat
// http://markitup.jaysalvat.com/
// -------------------------------------------------------------------
// Textile tags example
// http://en.wikipedia.org/wiki/Textile_(markup_language)
// http://www.textism.com/
// -------------------------------------------------------------------
// Feel free to add more tags
// -------------------------------------------------------------------
mySettings = {
	previewParserPath:	'', // path to your Textile parser
	onShiftEnter:		{keepDefault:false, replaceWith:'\n\n'},
	markupSet: [
		{name:'Negrito', key:'B', closeWith:'*', openWith:'*'},
		{name:'Italíco', key:'I', closeWith:'_', openWith:'_'},
		{name:'Riscado', key:'S', closeWith:'-', openWith:'-'},
		{separator:'---------------' },
		{name:'Lista', openWith:'(!(* |!|*)!)'},
		{name:'Lista numérica', openWith:'(!(# |!|#)!)'}
	]
};