<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Davi Ferreira Blog - Interface Drag and Drop - exemplo</title>
<script type="text/javascript" src="./js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="./js/interface.js"></script>
<link href="./css/kanban.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<!-- debug -->
<div style="background-color: #f7f7f7;padding:10px;border-bottom:1px solid #333;">
	<p><strong>Serialize</strong> (posi��o dos elementos nos divs.recebeDrag)</p>
    <p>Esquerda: <input type="text" id="ser-e" size="100" /></p>
    <p>Direita: <input type="text" id="ser-d" size="100" /></p>
</div>
<!-- fim debug -->

<div id="voltar" style="text-align:center;border: 1px dotted silver; padding: 4px;"><a href="http://www.daviferreira.com/blog/2007/08/12/interface-drag-and-drop-com-jquery.html">Voltar para o post deste exemplo.</a><br /><a href="http://www.daviferreira.com/blog/2007/08/12/interface-drag-and-drop-com-jquery.html">http://www.daviferreira.com/blog/2007/08/12/interface-drag-and-drop-com-jquery.html</a></div>

<div id="drop-esquerda" class="recebeDrag">
  <div id="aassinados" class="itemDrag">
    <h2> abaixo-assinados mais aderidos</h2>
    <ul>
      <li>
        <p><a href="http://beta.democracia.com.br/aassinado/ver.php?codigo=1">Um milh�o de hectares ou um milh�o de reais? <small>7 assinaturas</small></a></p>
      </li>
      <li>
        <p><a href="http://beta.democracia.com.br/aassinado/ver.php?codigo=2">Majoramento da pena de pol�ticos corruptos <small>6 assinaturas</small></a></p>
      </li>
      <li>
        <p><a href="http://beta.democracia.com.br/aassinado/ver.php?codigo=6">Pena de Morte para Politicos Corruptos <small>4 assinaturas</small></a></p>
      </li>
    </ul>
  </div>
  <!-- / box abaxo-assinados -->
  <div id="debates" class="itemDrag">
    <h2>debates mais pol�micos</h2>
    <ul>
      <li>
        <p><a href="http://beta.democracia.com.br/debate/ver.php?codigo=2">Majoramento da pena de pol�ticos corruptos<small>8 coment�rios - 4 apoiaram </small></a></p>
      </li>
      <li>
        <p><a href="http://beta.democracia.com.br/debate/ver.php?codigo=4">Vamos apagar o apag�o a�reo?<small>7 coment�rios - 2 apoiaram </small></a></p>
      </li>
      <li>
        <p><a href="http://beta.democracia.com.br/debate/ver.php?codigo=1">Um milh�o de hectares ou um milh�o de reais?<small>5 coment�rios - 2 apoiaram </small></a></p>
      </li>
    </ul>
  </div>
  <!-- / box debates -->
</div>
<!-- / box drop-esquerda -->
<div id="drop-direita" class="recebeDrag">
  <div id="perfis" class="itemDrag">
    <h2>perfis cadastrados</h2>
    <ul>
      <li>
        <p><a href="http://beta.democracia.com.br/perfil/ver.php?codigo=37">Bruno Magrani de Souza<small>Rio de Janeiro - Rio de Janeiro
          <!-- -  anos | Empres�rio -->
          </small></a></p>
      </li>
      <li>
        <p><a href="http://beta.democracia.com.br/perfil/ver.php?codigo=36">Armando Silva<small>Niter&oacute;i - Rio de Janeiro
          <!-- -  anos | Empres�rio -->
          </small></a></p>
      </li>
      <li>
        <p><a href="http://beta.democracia.com.br/perfil/ver.php?codigo=35">Bruna<small>Rio de Janeiro - Rio de Janeiro
          <!-- -  anos | Empres�rio -->
          </small></a></p>
      </li>
    </ul>
  </div>
  <!-- / box perfil cadastrado -->
  <div id="blog" class="itemDrag">
    <h2>democracia blog</h2>
    <ul>
      <li>
        <p><a href="http://www.democracia.com.br/blog/?p=34" rel="external">Esbo�ando a inclus�o digital... <small>06/08/2007 13:47</small></a></p>
      </li>
      <li>
        <p><a href="http://www.democracia.com.br/blog/?p=33" rel="external">Bate-rebate entre "Cansamos" e "Cansei" � mais do mesmo cansa�o <small>31/07/2007 18:05</small></a></p>
      </li>
      <li>
        <p><a href="http://www.democracia.com.br/blog/?p=22" rel="external">Quando a est�tica trope�a na �tica... e na dor do luto <small>20/07/2007 11:00</small></a></p>
      </li>
    </ul>
  </div>
  <!-- / box blog -->
</div>
<!-- / box drop-direita -->
<div class="result">sdfsdfds</div>


<script type="text/javascript">
$(document).ready(
	function () {
		$('div.recebeDrag').Sortable(
			{
				accept			: 'itemDrag',
				helperclass		: 'dragAjuda',
				activeclass 	: 'dragAtivo',
				hoverclass 		: 'dragHover',
				handle			: 'h2',
				opacity			: 0.7,
				onChange 		: function()
				{	    			 
					serialEsq = $.SortSerialize('drop-esquerda');
					serialDir = $.SortSerialize('drop-direita');
					$('#ser-e').val(serialEsq.hash);
					$('#ser-d').val(serialDir.hash);
					
					$.ajax({
						  url: 'kanban',
						  type: "POST",
						  success: function(data) {
						    $('.result').html(data);
						    alert('Load was performed.');
						  }
						});
					
				},
				onStart : function()
				{
					$.iAutoscroller.start(this, document.getElementsByTagName('body'));
				},
				onStop : function()
				{
					$.iAutoscroller.stop();
				}
			}
		);
	}
);
</script>
</body>
</html>