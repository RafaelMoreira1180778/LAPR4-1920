<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>


    <xsl:template match="/">
        Materias Primas:
        <xsl:apply-templates select="ChaoDeFabrica/MateriasPrimas/MateriaPrima"/>
        Categoria Materias Primas:
        <xsl:apply-templates select="ChaoDeFabrica/CategoriasMateriasPrimas/CategoriaMateriasPrimas"/>
        Produtos:
        <xsl:apply-templates select="ChaoDeFabrica/Produtos/Produto"/>
        Linhas Producao:
        <xsl:apply-templates select="ChaoDeFabrica/LinhasProducao/LinhaProducao"/>
        Encomendas:
        <xsl:apply-templates select="ChaoDeFabrica/Encomendas/Encomenda"/>
        Maquinas:
        <xsl:apply-templates select="ChaoDeFabrica/Maquinas/Maquina"/>
        Depositos:
        <xsl:apply-templates select="ChaoDeFabrica/Depositos/Deposito"/>
        Ordens Producao:
        <xsl:apply-templates select="ChaoDeFabrica/OrdensProducao/OrdemProducao"/>


    </xsl:template>

    <xsl:template match="MateriaPrima">
        <xsl:value-of select="concat('&#xa;', 'Id - ', @id, '&#xa;', 'Descrição - ', Descricao, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="CategoriaMateriasPrimas">
        <xsl:value-of select="concat('&#xa;', 'Id - ', @id, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Produto">
        <xsl:value-of select="concat('&#xa;', 'Id - ', @id, '&#xa;', 'Id Comercial - ', IDComercial, '&#xa;', 'Descrição Breve - ',
        DescricaoBreve, '&#xa;', 'Descrição Completa - ', DescricaoCompleta, '&#xa;', 'Unidade - ', Unidade, '&#xa;', 'Id Categoria - ',
        IDCategoria, '&#xa;', '&#xa;')"/>
    </xsl:template>

    <xsl:template match="LinhaProducao">
        <xsl:value-of select="concat('&#xa;', 'Id - ', @id, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Encomenda">
        <xsl:value-of select="concat('&#xa;', 'Id Encomeda - ', @id, '&#xa;', 'Id Ordem Produção - ', OrdemProducao/@id, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Maquina">
        <xsl:value-of select="concat('&#xa;', 'Id - ', @id, '&#xa;', 'Número Serie - ', numeroSerie, '&#xa;', 'Fabricante - ',
        Fabricante, '&#xa;', 'Modelo - ', Modelo, '&#xa;', 'Ano Fabrico - ', AnoFabrico, '&#xa;', 'Descricao - ',
        Descricao, '&#xa;', 'Ano Instalacao - ', Instalacao/Data, '&#xa;','Hora Instalacao - ', Instalacao/Hora, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="Deposito">
        <xsl:value-of select="concat('&#xa;', 'Id - ', @id, '&#xa;', 'Descricao - ', Descricao, '&#xa;')"/>
    </xsl:template>

    <xsl:template match="OrdemProducao">
        <xsl:value-of select="concat('&#xa;', 'Id - ', @id, '&#xa;', 'Produto - ', Produto/@id, '&#xa;', 'Data Emissão - ',
        DataEmissao, '&#xa;', 'Data Previsão - ', DataPrevisao, '&#xa;', 'Encomendas - ',
        encomendas, '&#xa;', 'Quantidade - ', Quantidade, '&#xa;', 'UnidadeMedida - ',
        UnidadeMedida, '&#xa;')"/>
    </xsl:template>
</xsl:stylesheet>


